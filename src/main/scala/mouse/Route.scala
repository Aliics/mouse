package mouse

import java.net.URI
import scala.concurrent.Future

case class Route(
  matcher: RouteMatcher,
  handler: Request => Future[Response],
)

type RouteMatcher = (Method, URI) => Boolean 
