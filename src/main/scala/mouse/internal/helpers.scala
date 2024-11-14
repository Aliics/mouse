package mouse.internal

import mouse.headersToHeaderValues
import mouse.types.Request
import java.io.{ByteArrayInputStream, InputStream, OutputStream}
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

inline private[mouse] def blockCall[T](futureThunk: Future[T]): T =
  Await.result(futureThunk, Duration.Inf)

inline private[mouse] def tryToEither[L, R](inline f: => R, inline l: String => L): Either[L, R] =
  try
    Right(f)
  catch
    case e: Throwable => Left(l(e.getMessage))

inline private[mouse] def serializeHeaders(headers: Map[String, String]) =
  headers.map((k, v) => s"$k: $v").toList.mkString("\r\n").getBytes

inline private[mouse] def stringToStream(s: String) =
  ByteArrayInputStream(s.to(LazyList).map(_.toByte).toArray)

inline private[mouse] def uriParts(req: Request) =
  req.uri.getPath.stripPrefix("/").split("/")

/**
 * Write all the standard content for an HTTP Request/Response. Do this over an [[OutputStream]].
 */
inline private[mouse] def writeHttpToOutputStream(outputStream: OutputStream)(
  statusLine: String,
  headers: Map[String, String],
  body: InputStream,
): Unit =
  outputStream.write(s"$statusLine\r\n".getBytes)
  outputStream.write(serializeHeaders(headers))

  // If there is no headers, we don't want to add too many \r\n.
  // Otherwise a weird newline is at the start of the body.
  if headers.nonEmpty then outputStream.write("\r\n".getBytes)

  outputStream.write("\r\n".getBytes)

  // Content-Length as a valid Int or 0. If it's invalid or not present, it's ZERO!
  val contentLength = headers
    .contentLength
    .flatMap(_.toIntOption)
    .getOrElse(0)

  // Nothing to write. Skip this.
  if contentLength > 0 then outputStream.write(body.readNBytes(contentLength))

  outputStream.flush()
