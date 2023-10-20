package mouse

import org.scalatest.funsuite.AnyFunSuiteLike

class ResponseTest extends AnyFunSuiteLike {
  test("response serialization") {
    val response = Response(
      statusCode = StatusCode.Ok,
      headers = Map(
        Headers.ContentLength -> "13",
        Headers.ContentType -> "plain/text",
      ),
      body = "Hello, Ollie!",
    )

    assert(response.serialized ==
      s"""HTTP/1.1 200 OK\r
        |Content-Length: 13\r
        |Content-Type: plain/text\r
        |\r
        |Hello, Ollie!
        |""".stripMargin)
  }
}
