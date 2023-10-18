package mouse

import scala.concurrent.Future

trait Route {
  def handle(req: Request): Future[Response]

  def apply(req: Request): Future[Response] = handle(req)
}
