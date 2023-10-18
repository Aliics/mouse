package mouse

case class Request(
  uri: String,
  method: Method,
  headers: Headers,
  body: String,
)

sealed trait Method
object Method {
  case object Connect extends Method
  case object Delete extends Method
  case object Get extends Method
  case object Head extends Method
  case object Options extends Method
  case object Patch extends Method
  case object Post extends Method
  case object Put extends Method
  case object Trace extends Method

  def apply(s: String): Method = s.toLowerCase match {
    case "connect" => Connect
    case "delete" => Delete
    case "get" => Get
    case "head" => Head
    case "options" => Options
    case "patch" => Patch
    case "post" => Post
    case "put" => Put
    case "trace" => Trace
  }
}
