package mouse

import mouse.internal.uriParts
import mouse.types.{Method, Request}

/**
 * Determine if the route matches for a [[Request]].
 * We need to know about the [[Method]]s and [[RoutePart]]s.
 *
 * We don't simply store a closure because we need to introspect this when utilizing route parameters.
 */
case class RouteMatcher(
  allowedMethods: Seq[Method],
  routeParts: Seq[RoutePart],
):
  /**
   *
   * @param req [[Request]]
   * @return Do the route constraints match the [[Request]]?
   */
  def apply(req: Request): Boolean =
    val ps = uriParts(req)
    allowedMethods.contains(req.method) &&
      // Early exit check. Also avoid issues where the given URI is longer than the route.
      routeParts.length == ps.length &&
      // Each part matches or it is a param.
      routeParts.zip(ps).forall:
        case (l: String, r) => l == r
        case (RouteParam(_), _) => true

/**
 * Either a strictly matched string or user input referenced by name.
 * These are the parts of a URI which are delimited by the / character.
 */
type RoutePart = String | RouteParam

/**
 * A part of a URI which is used as a [[String]] parameter. The value can be extracted from [[Request]]s
 * with the [[routeParam]] function.
 * @param name Name to reference the parameter
 */
case class RouteParam(name: String)
