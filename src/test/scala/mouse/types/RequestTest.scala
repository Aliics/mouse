package mouse.types

import mouse.errors.ParseError
import mouse.internal.{blockCall, stringToStream}
import org.scalatest.funsuite.AnyFunSuiteLike

import java.net.URI
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

class RequestTest extends AnyFunSuiteLike:
  test("standard GET request"):
    val Right(request) = parseRequest("GET / HTTP/1.1\r\nContent-Type: plain/text\r\n\r\n"): @unchecked

    assert(request.method == Method.Get)
    assert(request.uri == URI.create("/"))
    assert(request.version == Version(1, 1))
    assert(request.headers == Map(Headers.ContentType -> "plain/text"))

  test("standard POST request"):
    val Right(request) = parseRequest(
      "POST /create HTTP/1.1\r\nContent-Length: 13\r\nContent-Type: application/json\r\n\r\n{\"foo\":\"bar\"}",
    ): @unchecked

    assert(request.method == Method.Post)
    assert(request.uri == URI.create("/create"))
    assert(request.version == Version(1, 1))
    assert(request.headers == Map(
      Headers.ContentLength -> "13",
      Headers.ContentType -> "application/json",
    ))
    assert(request.textBlocking() == """{"foo":"bar"}""")

  test("serialize request"):
    val request = Request(
      method = Method.Get,
      uri = URI `create` "/hello",
      version = Version(1, 1),
      headers = Map(
        Headers.ContentType -> "application/json",
        Headers.ContentLength -> "2",
      ),
      body = stringToStream("{}"),
    )

    assert(request.toString == "GET /hello HTTP/1.1\r\nContent-Type: application/json\r\nContent-Length: 2\r\n\r\n{}")

  def parseRequest(raw: String): Either[ParseError, Request] =
    blockCall(Request(stringToStream(raw)))
