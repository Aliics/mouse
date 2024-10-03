package mouse.types

import mouse.errors.ParseError
import mouse.internal.{blockCall, stringToStream}
import mouse.types.{Response, Status, Version}
import org.scalatest.funsuite.AnyFunSuiteLike

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.io.Source

class ResponseTest extends AnyFunSuiteLike:
  test("standard 200 OK response"):
    val Right(response) = parseResponse("HTTP/1.1 200 OK\r\nContent-Length: 1\r\n\r\nE"): @unchecked

    val body = Source.fromInputStream(response.body).mkString
    assert(response.version == Version(1, 1))
    assert(response.status == Status.Ok)
    assert(response.headers == Map("Content-Length" -> "1"))
    assert(body == "E")

  test("serialize response"):
    val response = Response(
      version = Version(1, 1),
      status = Status.Ok,
      headers = Map(
        "Content-Type" -> "application/json",
        "Content-Length" -> "2",
      ),
      body = stringToStream("{}"),
    )

    assert(response.toString == "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\nContent-Length: 2\r\n\r\n{}")

  def parseResponse(raw: String): Either[ParseError, Response] =
    blockCall(Response(stringToStream(raw)))
