package mouse

import mouse.errors.ParseError

import java.io.{ByteArrayOutputStream, InputStream, OutputStream}
import java.net.URI
import scala.annotation.tailrec
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
  def apply(inputStream: InputStream)(using ExecutionContext): Future[Either[ParseError, Request]] =
    /**
     * Recursively searches for the needle to occur when reading from [[inputStream]]. When the needle is found, reading
     * stops. If we exhaust all the data from the stream, we simply stop reading.
     *
     * @param needle The string to look for from the [[inputStream]].
     * @param acc    Accumulating string from the [[inputStream]].
     * @return [[acc]]
     */
    @tailrec
    def readUntil(needle: String, acc: String = ""): String =
      val c = inputStream.read()
      if c == -1 then acc // Exhausted input stream data.
      else
        val haystack = acc + c.toChar
        if haystack `endsWith` needle then haystack dropRight needle.length // We found our result!
        else readUntil(needle, haystack) // We haven't found our needle yet, keep searching.

    /**
     * Recursively read from [[inputStream]] until there are no more header lines.
     *
     * @return Map of header key-value pairs.
     */
    def parseHeaders: Either[ParseError, Map[String, String]] =
      readUntil("\r\n") match
        case s"$k: $v" => parseHeaders.map(Map(k -> v) ++ _) // Standard header line.
        case "" => Right(Map.empty) // Nothing was on the same line.
        case s => Left(ParseError(s"Invalid header format: $s"))

    Future:
      for
        method <- Method(readUntil(" "))

        // "create" could throw. Wrap exceptions as a Left[ParseError].
        uri <- tryToEither(URI `create` readUntil(" "), ParseError.apply)

        version <- Version(readUntil("\r\n"))
        headers <- parseHeaders
      yield Request(
        method = method,
        uri = uri,
        version = version,
        headers = headers,
        body = inputStream,
      )
