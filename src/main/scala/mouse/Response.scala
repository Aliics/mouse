package mouse

case class Response(
  statusCode: StatusCode,
  headers: Map[String, String],
  body: String,
)

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
