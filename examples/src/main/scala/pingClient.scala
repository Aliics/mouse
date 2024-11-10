import mouse.*
import mouse.types.*
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.ExecutionContext.Implicits.given
import scala.concurrent.Future

@main def pingClient(): Unit =
  given Logger = LoggerFactory.getLogger("examples")

  Server(
    routes(
      Method.Get / "ping" -> ping,
    ) *
  ).runBlocking(port = 8080)
