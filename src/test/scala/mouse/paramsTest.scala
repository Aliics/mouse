package mouse

import mouse.errors.UnknownParamNameError
import mouse.types.*
import org.scalatest.funsuite.AnyFunSuiteLike

import java.io.InputStream
import java.net.URI
import scala.concurrent.Future

class paramsTest extends AnyFunSuiteLike:
  given Request(
    method = Method.Get,
    uri = URI.create("/hello/bar?fizz=buzz"),
    version = Version(1, 1),
    headers = Map.empty,
    body = InputStream.nullInputStream,
    route = Some(Route(
      matcher = RouteMatcher(
        allowedMethods = Seq(Method.Get),
        routeParts = Seq("hello", RouteParam("foo")),
      ),
      handler = (req: Request) =>
        given Request = req
        Future.successful(Response.Ok()),
    )),
  )

  test("fetching existing route param"):
    assert(routeParam("foo") == "bar")

  test("fetching non-existent route param"):
    assertThrows[UnknownParamNameError](routeParam("bar"))
    assertThrows[UnknownParamNameError](routeParam("buzz"))

  test("fetching existing query params"):
    assert(queryParam("fizz") contains "buzz")

  test("fetching non-existent query params with defaults"):
    assert(queryParam("qux", or = "oh, wrong room") == "oh, wrong room")
