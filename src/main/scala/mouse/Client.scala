package mouse

import mouse.internal.{Constants, blockCall, stringToStream}
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
        version = Constants.DefaultVersion,
        headers = headers,
        body = InputStream.nullInputStream,
      )
    .flatMap(request(_, None))

  def get(path: String, headers: Map[String, String] = Map.empty): Future[Response] =
    Future:
      Request(
        method = Method.Get,
        uri = URI `create` path,
        version = Constants.DefaultVersion,
        headers = headers,
        body = InputStream.nullInputStream,
      )
    .flatMap(request(_, None))

  def options(path: String, headers: Map[String, String] = Map.empty, body: String = ""): Future[Response] =
    Future:
      Request(
        method = Method.Options,
        uri = URI `create` path,
        version = Constants.DefaultVersion,
        headers = headers,
        body = stringToStream(body),
      )
    .flatMap(request(_, Some(body.length)))

  def put(path: String, headers: Map[String, String] = Map.empty, body: String = ""): Future[Response] =
    Future:
      Request(
        method = Method.Put,
        uri = URI `create` path,
        version = Constants.DefaultVersion,
        headers = headers,
        body = stringToStream(body),
      )
    .flatMap(request(_, Some(body.length)))

  def post(path: String, headers: Map[String, String] = Map.empty, body: String = ""): Future[Response] =
    Future:
      Request(
        method = Method.Post,
        uri = URI `create` path,
        version = Constants.DefaultVersion,
        headers = headers,
        body = stringToStream(body),
      )
    .flatMap(request(_, Some(body.length)))

  def request(req: Request, contentLength: Option[Int]): Future[Response] =
    for
      s <- Future(Socket(host.stripPrefix(Constants.HttpPrefix), port))
      _ <- Future:
        req
          .copy(
            headers = req.headers
              .updated(Constants.HostHeader, host)
              .updatedWith(Constants.ContentLengthHeader)(_ => contentLength.map(_.toString))
          )
          .writeToStream(s.getOutputStream)
      resp <-
        Response(s.getInputStream).collect:
          case Right(r) => r
    yield resp
