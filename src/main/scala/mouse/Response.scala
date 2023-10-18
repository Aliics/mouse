package mouse

case class Response(
  statusCode: StatusCode,
  headers: Map[String, String],
  body: String,
)

object Ok {
  def apply(body: String, headers: Map[String, String] = Map()): Response =
    Response(StatusCode.Ok, headers, body)
}

object BadRequest {
  def apply(body: String, headers: Map[String, String] = Map()): Response =
    Response(StatusCode.BadRequest, headers, body)
}

object Unauthorized {
  def apply(body: String, headers: Map[String, String] = Map()): Response =
    Response(StatusCode.Unauthorized, headers, body)
}

object NotFound {
  def apply(body: String, headers: Map[String, String] = Map()): Response =
    Response(StatusCode.NotFound, headers, body)
}

object InternalServerError {
  def apply(body: String, headers: Map[String, String] = Map()): Response =
    Response(StatusCode.InternalServerError, headers, body)
}
