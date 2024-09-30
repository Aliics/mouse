package mouse

import java.io.InputStream

case class Response(
  version: Version,
  status: Status,
  headers: Map[String, String],
  body: InputStream,
):
  override def toString: String =
    serializeContent(
      statusLine = s"$version $status",
      headers = headers,
      bodyStream = body,
    )
