package mouse

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

type RoutePart = String | RouteParam

case class RouteParam(name: String)
