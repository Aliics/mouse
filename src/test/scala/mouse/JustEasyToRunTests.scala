package mouse

import mouse.types.*
import org.scalatest.Ignore
import org.scalatest.funsuite.AnyFunSuiteLike
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Ignore
class JustEasyToRunTests extends AnyFunSuiteLike:
  lazy val NameParam = "name"

  given logger: Logger =
    System.setProperty(org.slf4j.simple.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "Debug")
    LoggerFactory.getLogger("examples")

  test("server"):
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

  test("client"):
    val resp = Client("localhost", 8080).getBlocking("hello/alex")
    logger.info("Response: {}", resp.textBlocking())
