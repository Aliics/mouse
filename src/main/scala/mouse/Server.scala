package mouse

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

  def accept(): Future[Unit] =
    for {
      conn <- Future(server.accept())
      _ = unhandledConns.push(conn)
      _ <- accept()
    } yield ()

  def handle(): Future[Unit] = {
    def allUnhandledConns(): List[Socket] =
      if (unhandledConns.isEmpty) List()
      else unhandledConns.pop() :: allUnhandledConns()

    Future
      .traverse(allUnhandledConns()) { conn =>
        for {
          req <- parseRequest(conn)
          res <- routes(req.uri)(req)
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

    readRawReq().map { rawReq =>
      val head :: body = rawReq.split("\n\n", 2).toList
      val s"$method $uri $_" :: headers = head.split("\n").toList

      Request(
        uri = uri,
        method = Method(method),
        headers = headers.map(_.split(": ")).map(x => (x.head, x.last)).toMap,
        body = body.mkString("\n"),
      )
    }
  }

  private def writeResponse(conn: Socket, res: Response): Future[Unit] = Future {
    val raw =
      s"""HTTP/1.1 ${res.statusCode.code} ${res.statusCode.text}\r
         |${res.headers.mkString("\r\n")}\r
         |\r
         |${res.body}""".stripMargin
    conn.getOutputStream.write(raw.getBytes)
    conn.close()
  }
}
