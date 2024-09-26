package mouse

import org.scalatest.funsuite.AnyFunSuiteLike

import java.io.{ByteArrayInputStream, InputStream}
import java.net.URI
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

class RequestTest extends AnyFunSuiteLike:
  test("standard GET request"):
    val request = "GET / HTTP/1.1\r\nContent-Type: plain/text\r\n\r\n"

    val response = Await.result(Request(new ByteArrayInputStream(request.getBytes)), Duration.Inf)

    assert(response == Right(Request(
      method = Method.Get,
      uri = URI `create` "/",
      version = Version(1, 1),
      headers = Map.empty,
      body = InputStream.nullInputStream,
    )))
