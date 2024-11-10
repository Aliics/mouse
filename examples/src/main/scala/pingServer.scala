import mouse.*
import mouse.types.*
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.ExecutionContext.Implicits.given
import scala.concurrent.Future

lazy val PingPort = 8081

private def ping(using Request) = Future:
  Response.Ok(
    body = queryParam("msg") getOrElse "pong",
  )

/**
 * Very simple ping server with one handler for GET [[ping]].
 */
@main def pingServer(): Unit =
  given Logger = LoggerFactory.getLogger("pingServer")

  Server(
    routes(
      Method.Get / "ping" -> ping,
    ) *
  ).runBlocking(port = PingPort)
