# Mouse - HTTP Server Library

Small, simple, minimal HTTP server library with no dependencies.
Since it isn't doing too much in the background, it should also be pretty darn fast.

# Example

Simple little toy example with a few routes.
```scala
import mouse.Implicits._
import mouse.Method.Get

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object HelloWorld extends App {
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
    })
  )

  new Server(routes).runBlocking()
}
```
