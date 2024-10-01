package mouse

import scala.concurrent.Future

/**
 * Simple route definition for our [[Server]].
 *
 * Who is your endpoint and what does he do?
 */
case class Route(
  matcher: RouteMatcher,
  handler: Request => Future[Response],
)
