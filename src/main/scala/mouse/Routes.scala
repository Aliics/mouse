package mouse

class Routes private(
  private val routesMapping: Map[String, Map[Method, Route]]
) {
  def apply(method: Method, uri: String): Option[Route] = for {
    routesOnUri <- routesMapping.get(uri)
    route <- routesOnUri.get(method)
  } yield route

  def +(r: Routes): Routes =
    new Routes(routesMapping ++ r.routesMapping)
}

object Routes {
  type Path = (Option[Method], String)

  /**
   * Provide pairs of [[Path]] to [[Route]]. These map HTTP requests to specific routes.
   *
   * @param routeMappings Pairs of [[Path]] to [[Route]].
   * @return Routes.
   */
  def apply(routeMappings: (Path, Route)*): Routes = {
    new Routes(
      routeMappings
        .map { case (method -> uri, route) =>
          (method, if (uri.startsWith("/")) uri else s"/$uri", route)
        }
        .flatMap {
          case (Some(method), uri, route) =>
            List((uri, Map(method -> route)))
          case (None, uri, route) =>
            List((uri, Method.all.map(_ -> route).toMap))
        }
        .foldLeft(Map[String, Map[Method, Route]]()) {
          case (a, (uri, b)) if a.contains(uri) =>
            a.updated(uri, a(uri) ++ b)
          case (a, b) => a + b
        }
    )
  }
}
