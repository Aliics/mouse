package mouse

case class Request(
  uri: String,
  params: Params,
  method: Method,
  headers: Headers,
  body: String,
)

object Request {
  private[mouse] def parse(rawReq: String): Request = {
    val head :: body = rawReq.split("\n\n", 2).toList
    val s"$method $fullUri $_" :: headers = head.split("\n").toList
    val (uri, params) = parseUri(fullUri)

    Request(
      uri = uri,
      params = params,
      method = Method(method),
      headers = headers.map(_.split(": ")).map(x => (x.head, x.last)).toMap,
      body = body.mkString("\n"),
    )
  }

  private def parseUri(fullUri: String) = {
    val uri :: rawParams = fullUri.split("\\?", 2).toList

    val params = rawParams
      .flatMap(params => params.split("&"))
      .filter(!_.isBlank)
      .map(param => param.split("=", 2))
      .map(kv => kv(0) -> kv(1))
      .toMap

    uri -> params
  }
}

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

  def all: Set[Method] = Set(Connect, Delete, Get, Head, Options, Patch, Post, Put, Trace)

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
