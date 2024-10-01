package mouse

import mouse.Client.{HttpPrefix, HttpVersion}

import java.io.InputStream
import java.net.{Socket, URI}
import scala.concurrent.{ExecutionContext, Future}

class Client(host: String, port: Int = 80)(using ExecutionContext):
  def getBlocking(path: String): Response = blockCall(get(path))

  def get(path: String, headers: Map[String, String] = Map.empty): Future[Response] =
    for
      req <- Future:
        Request(
          method = Method.Get,
          uri = URI `create` path,
          version = HttpVersion,
          headers = headers,
          body = InputStream.nullInputStream,
        )
      s <- Future(Socket(host.stripPrefix(HttpPrefix), port))
      _ <- Future:
        req.writeToStream(s.getOutputStream)
      resp <-
        Response(s.getInputStream).collect:
          case Right(r) => r
    yield resp

object Client:
  private lazy val HttpVersion = Version(1, 1)
  private lazy val HttpPrefix = "http://"
