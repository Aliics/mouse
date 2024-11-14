package mouse

import mouse.internal.{Constants, blockCall, stringToStream}
import mouse.types.{Method, Request, Response, Version}
import org.slf4j.Logger

import java.io.InputStream
import java.net.{Socket, URI}
import scala.concurrent.{ExecutionContext, Future}

/**
 * HTTP Client class for a specific server, specified by the [[host]] parameter.
 *
 * Different types of requests, such as GET or POST requests, are made with the helper methods available on [[Client]].
 * While the underlying functionality is exposed with [[Client.request]], and a [[Request]] can be manually created. We
 * recommend utilizing methods like [[Client.get]] instead, to prevent user error and bad parameters.
 *
 * {{{
 *  // Here we are utilizing the blocking version of get.
 *  // In production, it will generally be best to use get instead.
 *  val resp = Client("localhost", 8080).getBlocking("hello/alex")
 * }}}
 *
 * @param host Host URL, like github.com to make requests against.
 * @param port Default 80 for HTTP, other ports can be specified.
 */
class Client(host: String, port: Int = 80)(using logger: Logger)(using ExecutionContext):
  inline def connectBlocking(path: String, headers: Map[String, String] = Map.empty): Response =
    blockCall(connect(path, headers))
  inline def deleteBlocking(path: String, headers: Map[String, String] = Map.empty): Response =
    blockCall(delete(path, headers))
  inline def getBlocking(path: String, headers: Map[String, String] = Map.empty): Response =
    blockCall(get(path, headers))
  inline def headBlocking(path: String, headers: Map[String, String] = Map.empty): Response =
    blockCall(head(path, headers))
  inline def optionsBlocking(path: String, headers: Map[String, String] = Map.empty): Response =
    blockCall(options(path, headers))
  inline def patchBlocking(path: String, headers: Map[String, String] = Map.empty, body: String = ""): Response =
    blockCall(patch(path, headers, body))
  inline def postBlocking(path: String, headers: Map[String, String] = Map.empty, body: String = ""): Response =
    blockCall(post(path, headers, body))
  inline def putBlocking(path: String, headers: Map[String, String] = Map.empty, body: String = ""): Response =
    blockCall(put(path, headers, body))
  inline def traceBlocking(path: String, headers: Map[String, String] = Map.empty, body: String = ""): Response =
    blockCall(trace(path, headers, body))

  /**
   * Invoke a CONNECT request against the [[host]].
   *
   * @param path    The uri to request
   * @param headers Optional headers to provide
   * @return
   */
  def connect(path: String, headers: Map[String, String] = Map.empty): Future[Response] =
    Future:
      Request(
        method = Method.Connect,
        uri = URI `create` path,
        version = Constants.DefaultVersion,
        headers = headers,
        body = InputStream.nullInputStream,
      )
    .flatMap(request(_))

  /**
   * Invoke a DELETE request against the [[host]].
   *
   * @param path    The uri to request
   * @param headers Optional headers to provide
   * @return
   */
  def delete(path: String, headers: Map[String, String] = Map.empty): Future[Response] =
    Future:
      Request(
        method = Method.Delete,
        uri = URI `create` path,
        version = Constants.DefaultVersion,
        headers = headers,
        body = InputStream.nullInputStream,
      )
    .flatMap(request(_))

  /**
   * Invoke a GET request against the [[host]].
   *
   * @param path    The uri to request
   * @param headers Optional headers to provide
   * @return
   */
  def get(path: String, headers: Map[String, String] = Map.empty): Future[Response] =
    Future:
      Request(
        method = Method.Get,
        uri = URI `create` path,
        version = Constants.DefaultVersion,
        headers = headers,
        body = InputStream.nullInputStream,
      )
    .flatMap(request(_))

  /**
   * Invoke a HEAD request against the [[host]].
   *
   * @param path    The uri to request
   * @param headers Optional headers to provide
   * @return
   */
  def head(path: String, headers: Map[String, String] = Map.empty): Future[Response] =
    Future:
      Request(
        method = Method.Head,
        uri = URI `create` path,
        version = Constants.DefaultVersion,
        headers = headers,
        body = InputStream.nullInputStream,
      )
    .flatMap(request(_))

  /**
   * Invoke a OPTIONS request against the [[host]].
   *
   * @param path    The uri to request
   * @param headers Optional headers to provide
   * @param body    Optional body string to stream as a part of the request
   * @return
   */
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

  /**
   * Invoke a PATCH request against the [[host]].
   *
   * @param path    The uri to request
   * @param headers Optional headers to provide
   * @param body    Optional body string to stream as a part of the request
   * @return
   */
  def patch(path: String, headers: Map[String, String] = Map.empty, body: String = ""): Future[Response] =
    Future:
      Request(
        method = Method.Patch,
        uri = URI `create` path,
        version = Constants.DefaultVersion,
        headers = headers,
        body = stringToStream(body),
      )
    .flatMap(request(_, Some(body.length)))

  /**
   * Invoke a PUT request against the [[host]].
   *
   * @param path    The uri to request
   * @param headers Optional headers to provide
   * @param body    Optional body string to stream as a part of the request
   * @return
   */
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

  /**
   * Invoke a POST request against the [[host]].
   *
   * @param path    The uri to request
   * @param headers Optional headers to provide
   * @param body    Optional body string to stream as a part of the request
   * @return
   */
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

  /**
   * Invoke a TRACE request against the [[host]].
   *
   * @param path    The uri to request
   * @param headers Optional headers to provide
   * @param body    Optional body string to stream as a part of the request
   * @return
   */
  def trace(path: String, headers: Map[String, String] = Map.empty, body: String = ""): Future[Response] =
    Future:
      Request(
        method = Method.Trace,
        uri = URI `create` path,
        version = Constants.DefaultVersion,
        headers = headers,
        body = stringToStream(body),
      )
    .flatMap(request(_, Some(body.length)))

  /**
   * Invoke a request against the [[host]]. This is the underlying function which is run for all client request methods.
   *
   * @param req HTTP request
   * @param contentLength Length of the body on the request
   * @return
   */
  def request(req: Request, contentLength: Option[Int] = None): Future[Response] =
    for
      _ <- Future(logger.debug("""Connecting to host "{}" on "{}"""", host, port))
      s <- Future(Socket(host.stripPrefix(Constants.HttpPrefix), port))
      _ <- Future:
        logger.debug("""Sending request to "{}": "{}"""", host, req.uri.toString)
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
