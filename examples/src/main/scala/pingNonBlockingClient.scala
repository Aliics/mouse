import mouse.*
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.ExecutionContext.Implicits.given
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

/**
 * Showcase non-blocking operations by querying the ping endpoint 10000 times using [[Future.sequence]].
 */
@main def pingNonBlockingClient(): Unit =
  given logger: Logger =
    // System.setProperty(org.slf4j.simple.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "Debug")
    LoggerFactory.getLogger("pingNonBlockingClient")

  val client = Client("localhost", PingPort)

  Await.result(
    Future.sequence:
      (0 to 10_000).toList.map: _ =>
        for
          resp <- client.get("ping")
          body <- resp.text
        yield
          logger.info("""/ping response: "{}"""", body)

          if body == "pong" then logger.info("Ping pong success!")
          else logger.error("Total failure!"),
    Duration.Inf,
  )
