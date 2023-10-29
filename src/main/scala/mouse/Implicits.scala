package mouse

import mouse.Routes.Path

import java.time.Instant
import scala.annotation.tailrec
import scala.concurrent.duration.{Duration, DurationInt}
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.language.implicitConversions
import scala.util.{Failure, Success}

object Implicits extends FromParamDefaults {
  implicit def partialFunctionToRoute(pf: PartialFunction[Request, Future[Response]]): Route = (req: Request) => pf(req)

  implicit def stringToMatchAllPath(s: String): Routes.Path = Path(None, s)
  implicit def methodToPath(m: Method): Routes.Path = Path(Some(m), "")

  implicit class ServerEx(server: Server) {
    /**
     * Effectively an alias for (with an additional blocking mechanism):
     *
     * {{{
     *   server.accept()
     *   server.handle()
     * }}}
     */
    def runBlocking(): Unit = {
      server.accept()
      Await.result(server.handle(), Duration.Inf)
    }
  }

  implicit class HeadersEx(headers: Headers) {
    def contentLength: Option[String] = headers.get(Headers.ContentLength)
    def accept: Option[String] = headers.get(Headers.Accept)
    def accessControlAllowCredentials: Option[String] = headers.get(Headers.AccessControlAllowCredentials)
    def accessControlAllowHeaders: Option[String] = headers.get(Headers.AccessControlAllowHeaders)
    def accessControlAllowMethods: Option[String] = headers.get(Headers.AccessControlAllowMethods)
    def accessControlAllowOrigin: Option[String] = headers.get(Headers.AccessControlAllowOrigin)
    def age: Option[String] = headers.get(Headers.Age)
    def allow: Option[String] = headers.get(Headers.Allow)
    def authorization: Option[String] = headers.get(Headers.Authorization)
    def cacheControl: Option[String] = headers.get(Headers.CacheControl)
    def connection: Option[String] = headers.get(Headers.Connection)
    def contentType: Option[String] = headers.get(Headers.ContentType)
    def cookie: Option[String] = headers.get(Headers.Cookie)
    def date: Option[String] = headers.get(Headers.Date)
    def eTag: Option[String] = headers.get(Headers.ETag)
    def forwarded: Option[String] = headers.get(Headers.Forwarded)
    def expires: Option[String] = headers.get(Headers.Expires)
    def from: Option[String] = headers.get(Headers.From)
    def host: Option[String] = headers.get(Headers.Host)
    def keepAlive: Option[String] = headers.get(Headers.KeepAlive)
    def lastModified: Option[String] = headers.get(Headers.LastModified)
    def location: Option[String] = headers.get(Headers.Location)
    def origin: Option[String] = headers.get(Headers.Origin)
    def upgrade: Option[String] = headers.get(Headers.Upgrade)
    def userAgent: Option[String] = headers.get(Headers.UserAgent)
    def xForwardedFor: Option[String] = headers.get(Headers.XForwardedFor)
    def xForwardedHost: Option[String] = headers.get(Headers.XForwardedHost)
  }

  private[mouse] implicit class FutureEx[T](future: Future[T])(implicit ec: ExecutionContext) {
    def timeout(duration: Duration): Future[Option[T]] = {
      val deadline = Instant.now.toEpochMilli + duration.toMillis

      @tailrec
      def eval(): Future[Option[T]] = {
        future.value match {
          case Some(Success(value)) =>
            Future.successful(Some(value))
          case Some(Failure(throwable)) =>
            Future.failed(throwable)
          case None =>
            if (Instant.now.toEpochMilli < deadline) eval()
            else Future.successful(None)
        }
      }

      Future(eval()).flatten
    }
  }
}
