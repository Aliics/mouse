package mouse

import mouse.Implicits.ServerEx
import org.scalatest.funsuite.AnyFunSuiteLike

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ServerTest extends AnyFunSuiteLike {
  test("stand up a server") {
    val routes = Routes(
      ("/echo", req => Future.successful(Ok(req.body))),
    )

    new Server(routes).runBlocking()
  }
}
