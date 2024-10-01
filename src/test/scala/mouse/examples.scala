package mouse

import mouse.types.*
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

given logger: Logger = LoggerFactory.getLogger("examples")

object ServerTest extends App:
  lazy val NameParam = "name"

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

object ClientTest extends App:
  val resp = Client("localhost", 8080).getBlocking("hello/world")
  logger.info("Response: {}", resp)
