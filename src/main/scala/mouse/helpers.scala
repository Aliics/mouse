package mouse

import java.io.InputStream
import scala.io.Source

private[mouse] def tryToEither[L, R](f: => R, l: String => L): Either[L, R] =
  try
    Right(f)
  catch
    case e: Throwable => Left(l(e.getMessage))

private[mouse] def serializeContent(
  statusLine: String,
  headers: Map[String, String],
  bodyStream: InputStream,
): String =
  val headerLines = headers.map((k, v) => s"$k: $v").toList
  val bodyLines = List("", Source.fromInputStream(bodyStream).mkString)

  // Join it all together with CRLF.
  (statusLine :: headerLines ++ bodyLines).mkString("\r\n")
