package mouse

import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext.Implicits.global

object ClientTest extends App:
  val logger = LoggerFactory.getLogger("ServerTest")

  val resp = Client("localhost", 8080).getBlocking("hello/world")

  logger.info("Response: {}", resp)
