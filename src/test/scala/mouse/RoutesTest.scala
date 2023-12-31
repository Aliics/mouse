package mouse

import mouse.Implicits._
import mouse.Method.{Delete, Get, Post}
import org.scalatest.funsuite.AnyFunSuiteLike

import scala.concurrent.Future

class RoutesTest extends AnyFunSuiteLike {
  test("fully-qualified singular simple get route") {
    val routes = Routes((Get / "simple", _ => Future.successful(Ok("hello"))))

    assert(routes(Get, "/simple").isDefined)
  }

  test("any method route") {
    val routes = Routes(("all", _ => Future.successful(Ok("hello"))))

    assert(routes(Get, "/all").isDefined)
    assert(routes(Post, "/all").isDefined)
    assert(routes(Delete, "/all").isDefined)
  }

  test("overloaded routes with different methods") {
    val routes = Routes(
      (Get / "overloaded", _ => Future.successful(Ok("hello"))),
      (Delete / "overloaded", _ => Future.successful(Ok("hello"))),
    )

    assert(routes(Get, "/overloaded").isDefined)
    assert(routes(Delete, "/overloaded").isDefined)
  }

  // This test is effectively just the ones above combined, but I just wanted to ensure it all worked together and
  // separately.
  test("many different route types") {
    val routes = Routes(
      (Get / "simple", _ => Future.successful(Ok("hello"))),

      ("all", _ => Future.successful(Ok("hello"))),

      (Get / "overloaded", _ => Future.successful(Ok("hello"))),
      (Delete / "overloaded", _ => Future.successful(Ok("hello"))),
    )

    assert(routes(Get, "/simple").isDefined)
    assert(routes(Get, "/all").isDefined)
    assert(routes(Post, "/all").isDefined)
    assert(routes(Delete, "/all").isDefined)
    assert(routes(Get, "/overloaded").isDefined)
    assert(routes(Delete, "/overloaded").isDefined)
  }

  test("concatenating routes") {
    val routes0 = Routes((Get / "simple", _ => Future.successful(Ok("hello"))))
    val routes1 = Routes((Get / "easy", _ => Future.successful(Ok("hi"))))

    val routes = routes0 ++ routes1

    assert(routes(Get, "/simple").isDefined)
    assert(routes(Get, "/easy").isDefined)
  }

  test("path params") {
    val routes = Routes((Get / "hello/:name", _ => Future.successful(Ok("hello"))))

    assert(routes(Get, "/hello/Ollie").isDefined)
  }

  test("missing param") {
    val routes = Routes((Get / "hello/:name", _ => Future.successful(Ok("hello"))))

    assert(routes(Get, "/hello").isEmpty)
  }
}
