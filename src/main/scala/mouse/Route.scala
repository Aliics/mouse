package mouse

import mouse.types.{Request, Response}

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

inline def routes(routePairs: (RouteMatcher, Request ?=> Future[Response])*) =
  routePairs.map: x =>
    Route(
      x._1,
      r =>
        given Request = r
        x._2
    )
