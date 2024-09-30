package mouse

import java.net.URI
import scala.concurrent.Future

case class Route(
  matcher: (Method, URI) => Boolean,
  handler: Request => Future[Response],
)
