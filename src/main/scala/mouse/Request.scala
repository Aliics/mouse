package mouse

case class Request(
  uri: String,
  method: Method,
  headers: Map[String, String],
  body: String,
)

sealed trait Method
object Method {
  case object Delete extends Method
  case object Get extends Method
  case object Options extends Method
  case object Post extends Method
  case object Put extends Method

  def apply(s: String): Method = s.toLowerCase match {
    case "delete" => Delete
    case "get" => Get
    case "options" => Options
    case "post" => Post
    case "put" => Put
  }
}
