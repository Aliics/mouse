package mouse

import java.time.Instant
import scala.annotation.tailrec
import scala.concurrent.duration.Duration
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Success

object FutureImplicitEx {
  implicit class FutureEx[T](future: Future[T])(implicit ec: ExecutionContext) {
    def timeout(duration: Duration): Future[Option[T]] = {
      val deadline = Instant.now.toEpochMilli + duration.toMillis

      @tailrec
      def eval(): Future[Option[T]] = {
        future.value match {
          case Some(Success(value)) =>
            Future.successful(Some(value))
          case None =>
            if (Instant.now.toEpochMilli < deadline) eval()
            else Future.successful(None)
        }
      }

      eval()
    }
  }
}
