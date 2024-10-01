package mouse

import java.io.ByteArrayInputStream
import scala.concurrent.Future

def routes(routePairs: (RouteMatcher, Request ?=> Future[Response])*) =
  routePairs.map: x =>
    Route(
      x._1,
      r =>
        given Request = r
        x._2
    )

private[mouse] def tryToEither[L, R](f: => R, l: String => L): Either[L, R] =
  try
    Right(f)
  catch
    case e: Throwable => Left(l(e.getMessage))

private[mouse] def serializeHeaders(headers: Map[String, String]) =
  headers.map((k, v) => s"$k: $v").toList.mkString("\r\n").getBytes

private[mouse] def stringToStream(s: String) =
  ByteArrayInputStream(s.to(LazyList).map(_.toByte).toArray)

private[mouse] def uriParts(req: Request) =
  req.uri.getPath.stripPrefix("/").split("/")
