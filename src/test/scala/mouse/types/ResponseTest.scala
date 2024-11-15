package mouse.types

import mouse.errors.ParseError
import mouse.headersToHeaderValues
import mouse.internal.{blockCall, stringToStream}
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
    assert(response.headers == Map(Headers.ContentLength -> "1"))
    assert(body == "E")

  test("serialize response"):
    val response = Response(
      version = Version(1, 1),
      status = Status.Ok,
      headers = Map(
        Headers.ContentType -> "application/json",
        Headers.ContentLength -> "2",
      ),
      body = stringToStream("{}"),
    )

    assert(response.toString == "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\nContent-Length: 2\r\n\r\n{}")

  test("read text body"):
    val Right(response) = parseResponse(
      "HTTP/1.1 200 OK\r\nContent-Length: 126\r\n\r\nIt's a God awful small affair. To the girl with the mousy hair. But her mommy is yelling no. And her daddy has told her to go.",
    ): @unchecked

    assert(response.textBlocking() ==
      "It's a God awful small affair. To the girl with the mousy hair. But her mommy is yelling no. And her daddy has told her to go."
    )

  test("case-insensitive header value extractions"):
    val Right(response) = parseResponse(
      s"""HTTP/1.1 200 OK\r
         |Content-Type: application/json\r
         |Content-Length: 2\r
         |Authorization: Bearer 3ccccfce-64d6-493f-84fc-bc03053823fd\r
         |x-forwarded-host: google.com\r
         |keep-Alive: 86400\r
         |\r
         |{}""".stripMargin,
    ): @unchecked

    assert(response.headers.contentType.contains("application/json"))
    assert(response.headers.contentLength.contains("2"))
    assert(response.headers.authorization.contains("Bearer 3ccccfce-64d6-493f-84fc-bc03053823fd"))
    assert(response.headers.xForwardedHost.contains("google.com"))
    assert(response.headers.keepAlive.contains("86400"))
    assert(response.headers.values.toSeq.contains("application/json")) // Confirming that we can just use headers

  def parseResponse(raw: String): Either[ParseError, Response] =
    blockCall(Response(stringToStream(raw)))
