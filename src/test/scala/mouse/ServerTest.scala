package mouse

import mouse.Implicits._
import mouse.Method.Get
import mouse.Params.required
import org.scalatest.funsuite.AnyFunSuiteLike

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ServerTest extends AnyFunSuiteLike {
  test("stand up a server") {
    val routes = Routes(
      ("/echo", req => Future.successful(Ok(req.body))),
      (Get / "/hello", implicit req => Future {
        val name = required("name")
        Ok(s"""Hello, $name!""")
      }),
    )

    new Server(routes).runBlocking()
  }
}
