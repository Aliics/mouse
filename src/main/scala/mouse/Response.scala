package mouse

import mouse.Implicits.HeadersEx

/**
 * Outbound HTTP Response.
 *
 * @param statusCode HTTP Status Code (example: [[Ok]])
 * @param headers    Map of HTTP Headers.
 * @param body       A string representing a body.
 */
case class Response(
  statusCode: StatusCode,
  headers: Headers,
  body: String,
) {
  def serialized: String =
    s"""HTTP/1.1 ${statusCode.code} ${statusCode.text}\r
       |${headers.map(h => s"${h._1}: ${h._2}").mkString("\r\n")}\r
       |\r
       |$body""".stripMargin
}

object Response {
  /**
   * Outbound HTTP Response.
   *
   * @param statusCode HTTP Status Code (example: [[Ok]])
   * @param headers    Map of HTTP Headers.
   * @param body       A string representing a body.
   */
  def apply(statusCode: StatusCode, headers: Headers, body: String): Response = {
    val headersWithInferredValues = Map.newBuilder[String, String]
    headersWithInferredValues ++= headers

    if (body.nonEmpty && headers.contentLength.isEmpty) {
      // Add Content-Length to the response from the length of the body.
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
