package mouse.types

import mouse.Route
import mouse.errors.ParseError
import mouse.internal.{InputParser, tryToEither, writeHttpToOutputStream}

import java.io.{ByteArrayOutputStream, InputStream, OutputStream}
import java.net.URI
import scala.concurrent.{ExecutionContext, Future}

/**
 * HTTP Request type.
 *
 * Generally constructing a [[Request]] directly is not recommended. Instead, prefer using [[mouse.Client]]'s request
 * methods, such as [[mouse.Client.get]].
 *
 * @param method HTTP Method
 * @param uri Resource being requested
 * @param version HTTP version (1.1 only for now)
 * @param headers Key-value map entries
 * @param body Body byte stream
 */
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
