package mouse

import scala.collection.mutable
import scala.concurrent.Future

class Routes {
  private val routesMapping: mutable.Map[String, Route] = mutable.Map()

  def apply(uri: String): Route = routesMapping(uri)
}

object Routes {
  def apply(routeMappings: (String, Route)*): Routes = {
    val routes = new Routes
    routes.routesMapping.addAll(routeMappings)
    routes
  }
}
