package mouse

import mouse.errors.ParseError
import org.scalatest.funsuite.AnyFunSuiteLike

import java.net.URI
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.io.Source

class RequestTest extends AnyFunSuiteLike:
  test("standard GET request"):
    val Right(response) = parseRequest("GET / HTTP/1.1\r\nContent-Type: plain/text\r\n\r\n"): @unchecked

    assert(response.method == Method.Get)
    assert(response.uri == URI.create("/"))
    assert(response.version == Version(1, 1))
    assert(response.headers == Map("Content-Type" -> "plain/text"))

  test("standard POST request"):
    val Right(response) = parseRequest(
      "POST /create HTTP/1.1\r\nContent-Type: application/json\r\n\r\n{\"foo\":\"bar\"}",
    ): @unchecked

    val body = Source.fromInputStream(response.body).mkString
    assert(response.method == Method.Post)
    assert(response.uri == URI.create("/create"))
    assert(response.version == Version(1, 1))
    assert(response.headers == Map("Content-Type" -> "application/json"))
    assert(body == """{"foo":"bar"}""")

  test("serialize request"):
    val request = Request(
      method = Method.Get,
      uri = URI `create` "/hello",
      version = Version(1, 1),
      headers = Map("Content-Type" -> "application/json"),
      body = stringToStream("{}"),
    )

    assert(request.toString == "GET /hello HTTP/1.1\r\nContent-Type: application/json\r\n\r\n{}")

  def parseRequest(raw: String): Either[ParseError, Request] =
    Await.result(Request(stringToStream(raw)), Duration.Inf)
