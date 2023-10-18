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
  val routes = Routes(
    ("/echo", req => Future.successful(Ok(req.body))),
    (Get / "/hello", { req =>
      val name = req.params.getOrElse("name", "World")
      Future.successful(Ok(s"""Hello, $name!"""))
    }),
  )

  new Server(routes).runBlocking()
}
```
