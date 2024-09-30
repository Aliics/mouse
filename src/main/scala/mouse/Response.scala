package mouse

import java.io.{ByteArrayOutputStream, InputStream, OutputStream}

case class Response(
  version: Version,
  status: Status,
  headers: Map[String, String],
  body: InputStream,
):
  def writeToStream(outputStream: OutputStream): Unit =
    outputStream.write(s"$version $status\r\n".getBytes)
    outputStream.write(serializeHeaders(headers))
    outputStream.write("\r\n\r\n".getBytes)
    body.transferTo(outputStream)

  override def toString: String =
    val stream = ByteArrayOutputStream()
    writeToStream(stream)
    stream.toString

object Response:
  def Ok(headers: Map[String, String] = Map.empty, body: String = "")(using req: Request): Response =
    Response(
      version = req.version,
      status = Status.Ok,
      headers = headers,
      body = stringToStream(body),
    )

  def NotFound(headers: Map[String, String] = Map.empty, body: String = "")(using req: Request): Response =
    Response(
      version = req.version,
      status = Status.NotFound,
      headers = headers,
      body = stringToStream(body),
    )
