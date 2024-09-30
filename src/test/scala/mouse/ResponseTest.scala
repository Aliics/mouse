package mouse

import org.scalatest.funsuite.AnyFunSuiteLike

class ResponseTest extends AnyFunSuiteLike:
  test("serialize response"):
    val response = Response(
      version = Version(1, 1),
      status = Status.Ok,
      headers = Map("Content-Type" -> "application/json"),
      body = stringToStream("{}"),
    )

    assert(response.toString == "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\n\r\n{}")
