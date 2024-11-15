package mouse.types

import mouse.errors.ParseError
import mouse.internal.{InputParser, blockCall, readBodyFromSource, tryToEither, writeHttpToOutputStream}
import mouse.{Route, headersToHeaderValues}

import java.io.{ByteArrayOutputStream, InputStream, OutputStream}
import java.net.URI
import scala.concurrent.{ExecutionContext, Future}
import scala.io.{Codec, Source}

/**
 * HTTP Request type.
 *
 * Generally constructing a [[Request]] directly is not recommended. Instead, prefer using [[mouse.Client]]'s request
 * methods, such as [[mouse.Client.get]].
 *
 * @param method  HTTP Method
 * @param uri     Resource being requested
 * @param version HTTP version (1.1 only for now)
 * @param headers Key-value map entries
 * @param body    The raw body [[InputStream]], which was the original socket stream, with only the body's bytes
 *                remaining. It is recommended to use the [[text]] method for reading the body, as it handles content-length.
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
  /**
   * Read body blocking. Wrapper for [[text]].
   */
  def textBlocking()(using ExecutionContext, Codec): String =
    blockCall(text)

  /**
   * Read the body of the request as text, where the number of bytes to read is equal the Content-Length header.
   * Assuming the header is not present, the resulting string will be empty.
   *
   * If the request body has already been read by either the [[writeToStream]] or [[toString]] methods, then this will
   * result in an empty String. This is because the [[InputStream]] will already be exhausted.
   */
  def text(using ExecutionContext, Codec): Future[String] =
    readBodyFromSource(body, headers.contentLength.map(_.toInt))

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

/**
 * HTTP Request type.
 *
 * Generally constructing a [[Request]] directly is not recommended. Instead, prefer using [[mouse.Client]]'s request
 * methods, such as [[mouse.Client.get]].
 */
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
