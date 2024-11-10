import mouse.*
import mouse.types.*
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.ExecutionContext.Implicits.given
import scala.concurrent.Future

private def ping(using Request) = Future:
  val msg = queryParam("msg") getOrElse "pong"
  Response.Ok(body = s"$msg\n")

@main def pingServer(): Unit =
  given Logger = LoggerFactory.getLogger("examples")

  Server(
    routes(
      Method.Get / "ping" -> ping,
    ) *
  ).runBlocking(port = 8080)
