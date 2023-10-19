package mouse

import mouse.Implicits._
import mouse.Method.Get
import mouse.Params.{optional, required}
import org.scalatest.funsuite.AnyFunSuiteLike

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

class ServerTest extends AnyFunSuiteLike {
  test("stand up a server") {
    val bunnies = List("Ollie", "Mr. Ollie", "Sr. Ollie", "King Oliver I")

    val routes = Routes(
      // Simple echo. Just responds with the given body.
      // Works on all methods, but in the real-world, this should probably just be a POST/PUT.
      ("echo", req => Future.successful(Ok(req.body))),

      // /hello?name=NAME will greet the name given. "name" is required, so a 400 will be
      // returned if not given.
      (Get / "hello", implicit req => Future {
        val name = required[String]("name")
        Ok(s"""Hello, $name!""")
      }),

      // /bunnies?id=OPTIONAL_ID will respond with a list of bunnies. "id" is optional, so
      // that must be handled by us. Additionally, it must be Int-parseable.
      (Get / "bunnies", implicit req => Future {
        val fetched = optional[Int]("id")
          .map(i => List(bunnies(i)))
          .getOrElse(bunnies)

        Ok(fetched.mkString("[", ",", "]"))
      }),

      // This route will always timeout because the Server is configured to a 1 second timeout
      // and the route takes 2 seconds to complete.
      ("/will-timeout", req => Future {
        Thread.sleep(2000) // Heavy operation.
        Ok("Why didn't it timeout??")
      }),
    )

    new Server(routes, timeout = 1.second).runBlocking()
  }
}
