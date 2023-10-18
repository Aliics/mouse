package mouse

import mouse.Implicits._
import mouse.Method.{Get, Post}
import org.scalatest.funsuite.AnyFunSuiteLike

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ServerTest extends AnyFunSuiteLike {
  test("stand up a server") {
    val routes = Routes(
      ("/echo", req => Future.successful(Ok(req.body))),
      (Get / "/hello", { req =>
        Future.successful(Ok(s"""Hello, ${req.params.getOrElse("name", "World!")}!"""))
      }),
    )

    new Server(routes).runBlocking()
  }
}
