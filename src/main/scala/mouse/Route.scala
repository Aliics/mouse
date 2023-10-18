package mouse

import scala.concurrent.Future

class Route {
  def apply(req: Request): Future[Response] =
    Future.successful(Response(StatusCode.Ok, req.headers, req.body))
}
