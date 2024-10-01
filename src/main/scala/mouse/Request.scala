package mouse

import mouse.errors.ParseError
import mouse.internal.InputParser

import java.io.{ByteArrayOutputStream, InputStream, OutputStream}
import java.net.URI
import scala.concurrent.{ExecutionContext, Future}

case class Request(
  method: Method,
  uri: URI,
  version: Version,
  headers: Map[String, String],
  body: InputStream,

  /**
   * The route the request is now being used on. This is used for route parameters, more or less.
   */
  private[mouse] val route: Option[Route] = None,
):
  def writeToStream(outputStream: OutputStream): Unit =
    writeHttpToOutputStream(outputStream)(
      statusLine = s"$method $uri $version",
      headers = headers,
      body = body,
    )

  override def toString: String =
    val stream = ByteArrayOutputStream()
    writeToStream(stream)
    stream.toString

object Request:
  /**
   * Parse an HTTP Request from an [[InputStream]].
   *
   * @param inputStream UTF-8 character input stream.
   * @return Future of either a [[ParseError]] or the parsed [[Request]].
   */
  def apply(inputStream: InputStream)(using ExecutionContext): Future[Either[ParseError, Request]] = Future:
    val parser = InputParser(inputStream)

    for
      method <- Method(parser.readUntil(" "))

      // "create" could throw. Wrap exceptions as a Left[ParseError].
      uri <- tryToEither(URI `create` parser.readUntil(" "), ParseError.apply)

      version <- Version(parser.readUntil("\r\n"))
      headers <- parser.parseHeaders
    yield Request(
      method = method,
      uri = uri,
      version = version,
      headers = headers,
      body = inputStream,
    )
