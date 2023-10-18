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

sealed trait StatusCode {
  def code: Int

  def text: String
}
object StatusCode {
  case object Ok extends StatusCode {
    def code = 200
    def text = "OK"
  }
}
