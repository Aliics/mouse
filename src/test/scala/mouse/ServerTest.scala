package mouse

import org.scalatest.funsuite.AnyFunSuiteLike

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

class ServerTest extends AnyFunSuiteLike {
  test("stand up a server") {
    val routes = Routes(
      ("/hello", new Route()),
    )

    val server = new Server(routes)

    server.accept()

    Await.result(server.handle(), Duration.Inf)
  }
}
