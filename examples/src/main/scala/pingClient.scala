import mouse.*
import mouse.types.*
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.ExecutionContext.Implicits.given

/**
 * Simple blocking implementation of a GET request.
 */
@main def pingClient(): Unit =
  given logger: Logger =
    // System.setProperty(org.slf4j.simple.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "Debug")
    LoggerFactory.getLogger("pingClient")

  val respText = Client("localhost", PingPort).getBlocking("ping").textBlocking()

  logger.info("""/ping response: "{}"""", respText)

  if respText == "pong" then logger.info("Ping pong success!")
  else logger.error("Total failure!")
