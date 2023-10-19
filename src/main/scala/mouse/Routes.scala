package mouse

import scala.collection.mutable

class Routes {
  private val routesMapping: mutable.Map[String, Map[Method, Route]] = mutable.Map()

  def apply(method: Method, uri: String): Option[Route] = for {
    routesOnUri <- routesMapping.get(uri)
    route <- routesOnUri.get(method)
  } yield route
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
    val routes = new Routes
    routes.routesMapping.addAll {
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
    }

    routes
  }
}
