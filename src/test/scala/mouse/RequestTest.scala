package mouse

import org.scalatest.funspec.AnyFunSpec

class RequestTest extends AnyFunSpec {
  describe("A well-formed request") {
    it("should be able to create a Request if all values are populated") {
      val request = Request.parse(
        """GET /bunny?name=Ollie HTTP/1.1
          |Content-Length: 6
          |
          |Oliver""".stripMargin,
      )

      assert(request == Request(
        uri = "/bunny",
        params = Map("name" -> "Ollie"),
        method = Method.Get,
        headers = Map(Headers.ContentLength -> "6"),
        body = "Oliver",
      ))
    }

    it("should be able to create a Request with minimal values") {
      val request = Request.parse("POST /a HTTP/1.1")

      assert(request == Request(
        uri = "/a",
        params = Map(),
        method = Method.Post,
        headers = Map(),
        body = "",
      ))
    }
  }
}
