package mouse

import mouse.errors.ParseError

import java.io.InputStream
import java.net.URI
import scala.annotation.tailrec
import scala.concurrent.{ExecutionContext, Future}

case class Request(
  method: Method,
  uri: URI,
  version: Version,
  headers: Map[String, String],
  body: InputStream,
)

object Request:
  def apply(inputStream: InputStream)(using ExecutionContext): Future[Either[ParseError, Request]] =
    /**
     * Recursively searches for the needle to occur when reading from [[inputStream]]. When the needle is found, reading
     * stops. If we exhaust all the data from the stream, we simply stop reading.
     *
     * @param needle The string to look for from the [[inputStream]].
     * @param acc Accumulating string from the [[inputStream]].
     * @return [[acc]]
     */
    @tailrec
    def readUntil(needle: String, acc: String = ""): String =
      val c = inputStream.read()
      if c == -1 then acc // Exhausted input stream data.
      else
        val haystack = acc + c.toChar
        if haystack `endsWith` needle then haystack // We found our result!
        else readUntil(needle, haystack) // We haven't found our needle yet, keep searching.

    Future:
      for
        method <- Method(readUntil(" "))
        uri <- tryToEither(URI `create` readUntil(" "), ParseError.apply)
        version <- Version(readUntil("\r\n"))
      yield Request(
        method = method,
        uri = uri,
        version = version,
        headers = Map.empty,
        body = InputStream.nullInputStream,
      )
