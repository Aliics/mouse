import mouse.*
import mouse.types.*
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.ExecutionContext.Implicits.given

@main def pingClient(): Unit =
  given logger: Logger = LoggerFactory.getLogger("pingClient")

  val resp = Client("localhost", 8081).getBlocking("ping")
  val respText = resp.textBlocking()

  logger.info("""/ping response: "{}"""", respText)

  if respText == "pong" then logger.info("Ping pong success!")
  else logger.error("Total failure!")
