package mouse

import mouse.exceptions.BadRequestException

import java.io.{BufferedReader, InputStreamReader}
import java.net.{ServerSocket, Socket}
import java.util.concurrent.ConcurrentLinkedDeque
import scala.concurrent.{ExecutionContext, Future}

class Server(val routes: Routes, val address: String = ":8080")(implicit private val ec: ExecutionContext) {
  private val server = {
    val port = address.split(":")(1).toInt
    new ServerSocket(port)
  }

  private val unhandledConns = new ConcurrentLinkedDeque[Socket]()

  /**
   * Accept connections from the ServerSocket. Puts them in a deque to be processed later.
   */
  def accept(): Future[Unit] =
    for {
      conn <- Future(server.accept())
      _ = unhandledConns.push(conn)
      _ <- accept()
    } yield ()

  /**
   * Handle inbound connections as HTTP requests.
   */
  def handle(): Future[Unit] = {
    def allUnhandledConns(): List[Socket] =
      if (unhandledConns.isEmpty) List()
      else unhandledConns.pop() :: allUnhandledConns()

    Future
      .traverse(allUnhandledConns()) { conn =>
        for {
          req <- parseRequest(conn)
          res <- invokeRouteHandler(req)
          _ <- writeResponse(conn, res)
        } yield ()
      }
      .flatMap(_ => handle())
  }

  private def parseRequest(conn: Socket): Future[Request] = {
    val reader = new BufferedReader(new InputStreamReader(conn.getInputStream))
    def readRawReq(): Future[String] = Future {
      def readReqHeading(): String = {
        val line = reader.readLine()
        if (line.isBlank) "\n"
        else s"$line\n${readReqHeading()}"
      }

      val heading = readReqHeading()
      val contentLength = heading match {
        case s"${_}Content-Length: $lengthAndTail" =>
          lengthAndTail.split("\n").head.toInt
        case _ =>
          0
      }

      val body = new Array[Char](contentLength)
      reader.read(body, 0, contentLength)

      heading + body.mkString
    }

    readRawReq().map(Request.parse)
  }

  private def invokeRouteHandler(req: Request) = {
    routes(req.method, req.uri) match {
      case Some(route) =>
        route(req).recover {
          case BadRequestException(message) => BadRequest(message)
          case throwable => InternalServerError(throwable.getMessage)
        }
      case None =>
        Future.successful(NotFound(s"""Could not find route "${req.uri}".\n"""))
    }
  }

  private def writeResponse(conn: Socket, res: Response) = Future {
    conn.getOutputStream.write(res.serialized.getBytes)
    conn.getOutputStream.flush()
    conn.close()
  }
}
