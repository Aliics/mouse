package mouse

import mouse.Implicits.FutureEx
import mouse.exceptions._

import java.io.{BufferedReader, InputStreamReader}
import java.net.{ServerSocket, Socket}
import java.util.concurrent.ConcurrentLinkedDeque
import scala.concurrent.duration.{Duration, DurationInt}
import scala.concurrent.{ExecutionContext, Future}

/**
 * Handles all HTTP server tasks like managing connections and routing.
 *
 * Here is a small code snippet of how it looks to use [[Server]].
 * {{{
 *   val routes = Routes(
 *     (Get / "hello", _ => Future.successful(Ok("Hello, World!")))
 *   )
 *
 *   Server(routes).runBlocking()
 * }}}
 *
 * @param routes      The routes to handle valid requests on.
 * @param address     Address to host on as. ("[address]:port")
 * @param timeout     How long a request has to be fulfilled until a timeout occurs.
 * @param parallelism How many connections to handle in parallel.
 * @param ec          Context to use for all concurrency.
 */
class Server(
  val routes: Routes,
  val address: String = ":8080",
  val timeout: Duration = 30.seconds,
  val parallelism: Int = 10,
)(implicit private val ec: ExecutionContext) {
  private val server = {
    val port = address.split(":")(1).toInt
    new ServerSocket(port, parallelism)
  }

  private val unhandledConns = new ConcurrentLinkedDeque[Socket]()

  /**
   * Accept connections from the ServerSocket. Puts them in a deque to be processed later.
   */
  def accept(): Future[Unit] =
    for {
      _ <-
        if (unhandledConns.size() < parallelism)
          Future(unhandledConns.push(server.accept()))
        else {
          Future.successful(())
        }
      _ <- accept()
    } yield ()

  /**
   * Handle inbound connections as HTTP requests.
   */
  def handle(): Future[Unit] = {
    def allUnhandledConns(n: Int = parallelism): List[Socket] =
      if (unhandledConns.isEmpty || n <= 0) List()
      else unhandledConns.pop() :: allUnhandledConns(n - 1)

    // Connections are handled and not watched.
    // Processing on batch should not stop another from processing.
    Future.traverse(allUnhandledConns()) { conn =>
      (for {
        req <- parseRequest(conn)
        res <- invokeRouteHandler(req)
      } yield res)
        .timeout(timeout) // Handle request for as long as the timeout allows.
        .map {
          case Some(value) => value
          case None => Response(
            StatusCode.RequestTimeout,
            Headers(),
            s"Could not complete request in ${timeout.toString}.",
          )
        }
        .flatMap(writeResponse(conn, _))
    }.flatMap(_ => handle())
  }

  private def parseRequest(conn: Socket): Future[Request] = Future {
    implicit val r: Routes = routes

    val reader = new BufferedReader(new InputStreamReader(conn.getInputStream))

    def readReqHeading(): String = {
      val line = reader.readLine()
      if (line == null) readReqHeading()
      else if (line.isBlank) "\n"
      else s"$line\n${readReqHeading()}"
    }

    // To obtain the body, we need to know the length.
    // This is reasonable standard, so we will use the Content-Length header.
    val heading = readReqHeading()
    val contentLength = heading match {
      case s"${_}Content-Length: $lengthAndTail" =>
        lengthAndTail.split("\n").head.toInt
      case _ =>
        0
    }

    val body = new Array[Char](contentLength)
    reader.read(body, 0, contentLength)

    Request.parse(heading + body.mkString)
  }

  private def invokeRouteHandler(req: Request) = {
    routes(req.method, req.uri) match {
      case Some(route) =>
        route(req).recover {
          case BadRequestException(message) => BadRequest(message)
          case NotFoundException(message) => NotFound(message)
          case UnauthorizedException(message) => Unauthorized(message)
          case throwable => InternalServerError(throwable.getMessage)
        }
      case None =>
        Future.successful(NotFound(s"""Could not find route "${req.uri}".\n"""))
    }
  }

  private def writeResponse(conn: Socket, res: Response) = Future {
    conn.getOutputStream.write(res.serialized.getBytes)
    conn.getOutputStream.flush()
  }
}
