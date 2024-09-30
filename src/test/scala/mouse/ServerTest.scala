package mouse

import org.scalatest.Ignore
import org.scalatest.funsuite.AnyFunSuiteLike

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

@Ignore
class ServerTest extends AnyFunSuiteLike:
  test("test routes"):
    Await.result(Server(
      Route(
        matcher = Method.Get / "hello" / "friend",
        handler = implicit req => Future(Response.Ok(body = "{}"))
      )
    ).run(port = 8080), Duration.Inf)
