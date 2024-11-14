import mouse.*
import mouse.types.*
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.ExecutionContext.Implicits.given
import scala.concurrent.Future

lazy val EchoPort = 8082

private def echo(using req: Request) =
  for
    body <- req.text
    prefix = queryParam("prefix")
      .fold("")(x => s"$x:")
  yield Response.Ok(
    body = s"$prefix$body",
  )

/**
 * Very simple echo server with one handler for POST [[echo]].
 * The body of the request will be mirrored to the body of the response.
 */
@main def echoServer(): Unit =
  given Logger =
    System.setProperty(org.slf4j.simple.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "Debug")
    LoggerFactory.getLogger("echoServer")

  Server(
    routes(
      Method.Post / "echo" -> echo,
    ) *
  ).runBlocking(port = EchoPort)
