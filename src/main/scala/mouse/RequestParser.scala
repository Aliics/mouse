package mouse

object RequestParser {
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
