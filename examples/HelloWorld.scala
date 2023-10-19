import mouse.Implicits._
import mouse.Method.Get

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object HelloWorld extends App {
  val routes = Routes(
    ("/echo", req => Future.successful(Ok(req.body))),
    (Get / "/hello", implicit req => Future {
      val name = required("name")
      Ok(s"""Hello, $name!""")
    }),
  )

  new Server(routes).runBlocking()
}
