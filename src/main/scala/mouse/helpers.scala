package mouse

import java.io.{ByteArrayInputStream, InputStream, OutputStream}
import scala.concurrent.Future

inline def routes(routePairs: (RouteMatcher, Request ?=> Future[Response])*) =
  routePairs.map: x =>
    Route(
      x._1,
      r =>
        given Request = r
        x._2
    )

/* Functions below are private. Don't think these are useful to the consumer. */

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
) =
  outputStream.write(s"$statusLine\r\n".getBytes)
  outputStream.write(serializeHeaders(headers))

  // If there is no headers, we don't want to add too many \r\n.
  // Otherwise a weird newline is at the start of the body.
  if headers.nonEmpty then outputStream.write("\r\n".getBytes)
  
  outputStream.write("\r\n".getBytes)
  body.transferTo(outputStream)
