package mouse

import org.scalatest.Ignore
import org.scalatest.funsuite.AnyFunSuiteLike
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

//@Ignore
class ServerTest extends AnyFunSuiteLike:
  test("test routes"):
    lazy val NameParam = "name"

    given Logger = LoggerFactory.getLogger("ServerTest")

    def greetWorld(using Request) = Future:
      Response.Ok(body = "Hello, World!")

    def greetFriend(using Request) = Future:
      val name = queryParam("fullName") getOrElse routeParam(NameParam)
      Response.Ok(body = s"Hello, $name!")

    Server(
      routes(
        Method.Get / "hello" / "world" -> greetWorld,
        Method.Get / "hello" / RouteParam(NameParam) -> greetFriend,
      ) *
    ).runBlocking(port = 8080)
