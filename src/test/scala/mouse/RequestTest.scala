package mouse

import mouse.Implicits._
import mouse.Method.Post
import org.scalatest.funspec.AnyFunSpec

import scala.concurrent.Future

class RequestTest extends AnyFunSpec {
  private implicit lazy val routes: Routes = Routes()

  describe("A well-formed request") {
    it("should be able to create a Request if all values are populated") {
      val req = Request.parse(
        """GET /bunny?name=Ollie HTTP/1.1
          |Content-Length: 6
          |
          |Oliver""".stripMargin,
      )

      assert(req == Request(
        uri = "/bunny",
        pathParams = Map(),
        queryParams = Map("name" -> "Ollie"),
        method = Method.Get,
        headers = Map(Headers.ContentLength -> "6"),
        body = "Oliver",
      ))
    }

    it("should be able to create a Request with minimal values") {
      val req = Request.parse("POST /a HTTP/1.1")

      assert(req == Request(
        uri = "/a",
        pathParams = Map(),
        queryParams = Map(),
        method = Method.Post,
        headers = Map(),
        body = "",
      ))
    }

    it("should be able to add path params") {
      val routes: Routes = Routes((Post / "test/:id", _ => Future.successful(Ok("hi"))))

      val req = Request.parse("POST /test/123 HTTP/1.1")(routes)

      assert(req == Request(
        uri = "/test/123",
        pathParams = Map("id" -> "123"),
        queryParams = Map(),
        method = Method.Post,
        headers = Map(),
        body = "",
      ))
    }
  }

  describe("Request with missing data") {
    it("should require path params to be present") {
      val routes: Routes = Routes((Post / "test/:id", _ => Future.successful(Ok("hi"))))

      val req = Request.parse("POST /test HTTP/1.1")(routes)

      assert(req == Request(
        uri = "/test",
        pathParams = Map(),
        queryParams = Map(),
        method = Method.Post,
        headers = Map(),
        body = "",
      ))
    }
  }
}
