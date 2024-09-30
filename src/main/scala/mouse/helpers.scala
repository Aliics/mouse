package mouse

import java.io.ByteArrayInputStream

private[mouse] def tryToEither[L, R](f: => R, l: String => L): Either[L, R] =
  try
    Right(f)
  catch
    case e: Throwable => Left(l(e.getMessage))

private[mouse] def serializeHeaders(headers: Map[String, String]) =
  headers.map((k, v) => s"$k: $v").toList.mkString("\r\n").getBytes

private[mouse] def stringToStream(s: String) =
  ByteArrayInputStream(s.to(LazyList).map(_.toByte).toArray)
