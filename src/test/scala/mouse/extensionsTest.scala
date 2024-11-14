package mouse

import mouse.*
import mouse.types.*
import org.scalatest.funsuite.AnyFunSuiteLike

class extensionsTest extends AnyFunSuiteLike:
  test("method chaining"):
    assert((Method.Get & Method.Post) == RouteMatcher(
      allowedMethods = Seq(Method.Get, Method.Post),
      routeParts = Nil,
    ))
    assert((Method.Get & Method.Post & Method.Put) == RouteMatcher(
      allowedMethods = Seq(Method.Get, Method.Post, Method.Put),
      routeParts = Nil,
    ))

  test("basic route matcher"):
    assert((Method.Get / "hello") == RouteMatcher(
      allowedMethods = Seq(Method.Get),
      routeParts = Seq("hello"),
    ))

  test("hypothetical user deletion route"):
    assert(((Method.Options & Method.Post) / "users" / "delete" / RouteParam("id")) == RouteMatcher(
      allowedMethods = Seq(Method.Options, Method.Post),
      routeParts = Seq("users", "delete", RouteParam("id")),
    ))
