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

/**
 * Convert tuples of [[RouteMatcher]]s and request handling functions to [[Route]]s.
 *
 * The request handlers are functions taking an implicit [[Request]] to allow for our type conversions and for context
 * to be passed from the handler to a variety of helper functions.
 *
 * @param routePairs Routes and handler functions
 * @return
 */
inline def routes(routePairs: (RouteMatcher, Request ?=> Future[Response])*) =
  routePairs.map: x =>
    Route(
      x._1,
      r =>
        given Request = r
        x._2
    )
