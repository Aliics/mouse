package mouse

import org.scalatest.Ignore
import org.scalatest.funsuite.AnyFunSuiteLike
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

//@Ignore
class ServerTest extends AnyFunSuiteLike:
  test("test routes"):
    lazy val RouteName = "name"

    given Logger = LoggerFactory.getLogger("ServerTest")

    def greetWorld(using Request) = Future:
      Response.Ok(body = "Hello, World!")

    def greetFriend(using Request) = Future:
      val name = queryParam("fullName") getOrElse routeParam(RouteName)
      Response.Ok(body = s"Hello, $name!")

    Server(
      routes(
        Method.Get / "hello" / "world" -> greetWorld,
        Method.Get / "hello" / RouteParam(RouteName) -> greetFriend,
      ) *
    ).runBlocking(port = 8080)
