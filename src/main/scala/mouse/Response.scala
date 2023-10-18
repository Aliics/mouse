package mouse

import mouse.Implicits.HeadersEx

case class Response(
  statusCode: StatusCode,
  headers: Headers,
  body: String,
) {
  def serialized: String =
    s"""HTTP/1.1 ${statusCode.code} ${statusCode.text}\r
       |${headers.mkString("\r\n")}\r
       |${body}
       |""".stripMargin
}

object Response {
  def apply(statusCode: StatusCode, headers: Headers, body: String): Response = {
    val headersWithInferredValues = Map.newBuilder[String, String]
    headersWithInferredValues ++= headers

    if (body.nonEmpty && headers.contentLength.isEmpty) {
      headersWithInferredValues.addOne((Headers.ContentLength, body.length.toString))
    }

    new Response(statusCode, headersWithInferredValues.result(), body)
  }
}

object Ok {
  def apply(body: String, headers: Headers = Headers()): Response =
    Response(StatusCode.Ok, headers, body)
}

object BadRequest {
  def apply(body: String, headers: Headers = Headers()): Response =
    Response(StatusCode.BadRequest, headers, body)
}

object Unauthorized {
  def apply(body: String, headers: Headers = Headers()): Response =
    Response(StatusCode.Unauthorized, headers, body)
}

object NotFound {
  def apply(body: String, headers: Headers = Headers()): Response =
    Response(StatusCode.NotFound, headers, body)
}

object InternalServerError {
  def apply(body: String, headers: Headers = Headers()): Response =
    Response(StatusCode.InternalServerError, headers, body)
}
