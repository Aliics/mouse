package mouse

import mouse.Implicits._
import mouse.Method.Get
import org.scalatest.Ignore
import org.scalatest.funsuite.AnyFunSuiteLike

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Ignore
class ServerTest extends AnyFunSuiteLike {
  test("stand up a server") {
    val routes = Routes(
      ("/hello", req => Future.successful(Ok(req.body))),
      (Get / "/hello", { req =>
        val name = req.params.getOrElse("name", "World")
        Future.successful(Ok(s"""Hello, $name!"""))
      }),
    )

    new Server(routes).runBlocking()
  }
}
