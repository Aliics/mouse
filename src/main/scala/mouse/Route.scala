package mouse

import scala.concurrent.Future

case class Route(
  matcher: RouteMatcher,
  handler: Request => Future[Response],
)
