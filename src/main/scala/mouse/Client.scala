package mouse

import mouse.Client.{HttpPrefix, HttpVersion}
import mouse.internal.{blockCall, stringToStream}
import mouse.types.{Method, Request, Response, Version}

import java.io.InputStream
import java.net.{Socket, URI}
import scala.concurrent.{ExecutionContext, Future}

class Client(host: String, port: Int = 80)(using ExecutionContext):
  def deleteBlocking(path: String, headers: Map[String, String] = Map.empty): Response =
    blockCall(delete(path, headers))
  def getBlocking(path: String, headers: Map[String, String] = Map.empty): Response =
    blockCall(get(path, headers))
  def optionsBlocking(path: String, headers: Map[String, String] = Map.empty): Response =
    blockCall(options(path, headers))
  def postBlocking(path: String, headers: Map[String, String] = Map.empty, body: String = ""): Response =
    blockCall(post(path, headers, body))
  def putBlocking(path: String, headers: Map[String, String] = Map.empty, body: String = ""): Response =
    blockCall(put(path, headers, body))

  def delete(path: String, headers: Map[String, String] = Map.empty): Future[Response] =
    Future:
      Request(
        method = Method.Delete,
        uri = URI `create` path,
        version = HttpVersion,
        headers = headers,
        body = InputStream.nullInputStream,
      )
    .flatMap(request)

  def get(path: String, headers: Map[String, String] = Map.empty): Future[Response] =
    Future:
      Request(
        method = Method.Get,
        uri = URI `create` path,
        version = HttpVersion,
        headers = headers,
        body = InputStream.nullInputStream,
      )
    .flatMap(request)

  def options(path: String, headers: Map[String, String] = Map.empty, body: String = ""): Future[Response] =
    Future:
      Request(
        method = Method.Options,
        uri = URI `create` path,
        version = HttpVersion,
        headers = headers,
        body = stringToStream(body),
      )
    .flatMap(request)

  def put(path: String, headers: Map[String, String] = Map.empty, body: String = ""): Future[Response] =
    Future:
      Request(
        method = Method.Put,
        uri = URI `create` path,
        version = HttpVersion,
        headers = headers,
        body = stringToStream(body),
      )
    .flatMap(request)

  def post(path: String, headers: Map[String, String] = Map.empty, body: String = ""): Future[Response] =
    Future:
      Request(
        method = Method.Post,
        uri = URI `create` path,
        version = HttpVersion,
        headers = headers,
        body = stringToStream(body),
      )
    .flatMap(request)

  def request(req: Request): Future[Response] =
    for
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
