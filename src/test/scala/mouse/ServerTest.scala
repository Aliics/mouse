package mouse

import mouse.Status.Ok
import org.scalatest.funsuite.AnyFunSuiteLike

import java.io.ByteArrayInputStream
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration


class ServerTest extends AnyFunSuiteLike:
  test("foo"):
    Await.result(Server(
      Route(
        matcher = {
          case (Method.Get, _) => true
          case _ => false
        },
        handler = req => Future(Response(
          version = req.version,
          status = Ok,
          headers = Map("Content-Type" -> "application/json"),
          body = ByteArrayInputStream("{}".getBytes),
        ))
      )
    ).run(port = 8080), Duration.Inf)
