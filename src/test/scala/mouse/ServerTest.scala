package mouse

import org.scalatest.funsuite.AnyFunSuiteLike
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

//@Ignore
class ServerTest extends AnyFunSuiteLike:
  test("test routes"):
    given Logger = LoggerFactory.getLogger("ServerTest")

    def greetWorld(using Request) = Future:
      Response.Ok(body = "Hello, World!")

    def greetFriend(using Request) = Future:
      val name = queryParam("name", or = "Friend")
      Response.Ok(body = s"Hello, $name!")

    Server(
      routes(
        Method.Get / "hello" / "world" -> greetWorld,
        Method.Get / "hello" / "friend" -> greetFriend,
      ) *
    ).runBlocking(port = 8080)
