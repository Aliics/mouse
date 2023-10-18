import mouse.Implicits._
import mouse.Method.Get

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object HelloWorld extends App {
  val routes = Routes(
    ("/hello", req => Future.successful(Ok(req.body))),
    (Get / "/hello", { req =>
      val name = req.params.getOrElse("name", "World")
      Future.successful(Ok(s"""Hello, $name!"""))
    }),
  )

  new Server(routes).runBlocking()
}
