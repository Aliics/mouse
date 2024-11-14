package mouse.types

import mouse.errors.ParseError
import mouse.headersToHeaderValues
import mouse.internal.*

import java.io.{ByteArrayOutputStream, InputStream, OutputStream}
import scala.concurrent.{ExecutionContext, Future}
import scala.io.{Codec, Source}

/**
 * HTTP Request type.
 *
 * Generally constructing a [[Response]] directly is not recommended. Instead, prefer using the helper constructor 
 * functions, such as [[Response.Ok]].
 *
 * @param version HTTP version (1.1 only for now)
 * @param status  Response status code
 * @param headers Key-value map entries
 * @param body    Body byte stream
 */
case class Response(
  version: Version,
  status: Status,
  headers: Map[String, String],
  body: InputStream,
):
  /**
   * Read body blocking. Wrapper for [[text]].
   */
  def textBlocking()(using ExecutionContext, Codec): String =
    blockCall(text)

  /**
   * Read the body of the response as text, where the number of bytes to read is equal the Content-Length header.
   * Assuming the header is not present, we will attempt to read until the input is exhausted.
   *
   * If the response body has already been read by either the [[writeToStream]] or [[toString]] methods, then this will
   * result in an empty String. This is because the [[InputStream]] will already be exhausted.
   */
  def text(using ExecutionContext, Codec): Future[String] =
    readBodyFromSource(body, headers.contentLength.map(_.toInt))

  def writeToStream(outputStream: OutputStream): Unit =
    writeHttpToOutputStream(outputStream)(
      statusLine = s"$version $status",
      headers = headers,
      body = body,
    )

  override def toString: String =
    val stream = ByteArrayOutputStream()
    writeToStream(stream)
    stream.toString

/**
 * HTTP Request type.
 *
 * Generally constructing a [[Response]] directly is not recommended. Instead, prefer using the helper constructor 
 * functions, such as [[Response.Ok]].
 */
object Response:
  /**
   * Parse an HTTP Response from an [[InputStream]].
   *
   * @param inputStream UTF-8 character input stream.
   * @return Future of either a [[ParseError]] or the parsed [[Response]].
   */
  def apply(inputStream: InputStream)(using ExecutionContext): Future[Either[ParseError, Response]] = Future:
    val parser = InputParser(inputStream)

    for
      version <- Version(parser `readUntil` " ")
      status <- Status(parser `readUntil` "\r\n")
      headers <- parser.parseHeaders
    yield Response(
      version = version,
      status = status,
      headers = headers,
      body = inputStream,
    )

  inline def Continue(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.Continue, headers, body)
  inline def SwitchingProtocols(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.SwitchingProtocols, headers, body)
  inline def Processing(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.Processing, headers, body)
  inline def EarlyHints(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.EarlyHints, headers, body)
  inline def Ok(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.Ok, headers, body)
  inline def Created(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.Created, headers, body)
  inline def Accepted(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.Accepted, headers, body)
  inline def NonAuthoritativeInformation(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.NonAuthoritativeInformation, headers, body)
  inline def NoContent(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.NoContent, headers, body)
  inline def ResetContent(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.ResetContent, headers, body)
  inline def PartialContent(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.PartialContent, headers, body)
  inline def MultiStatus(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.MultiStatus, headers, body)
  inline def AlreadyReported(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.AlreadyReported, headers, body)
  inline def ImUsed(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.ImUsed, headers, body)
  inline def MultipleChoices(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.MultipleChoices, headers, body)
  inline def MovedPermanently(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.MovedPermanently, headers, body)
  inline def Found(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.Found, headers, body)
  inline def SeeOther(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.SeeOther, headers, body)
  inline def NotModified(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.NotModified, headers, body)
  inline def UseProxyDeprecated(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.UseProxyDeprecated, headers, body)
  inline def TemporaryRedirect(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.TemporaryRedirect, headers, body)
  inline def PermanentRedirect(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.PermanentRedirect, headers, body)
  inline def BadRequest(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.BadRequest, headers, body)
  inline def Unauthorized(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.Unauthorized, headers, body)
  inline def PaymentRequiredExperimental(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.PaymentRequiredExperimental, headers, body)
  inline def Forbidden(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.Forbidden, headers, body)
  inline def NotFound(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.NotFound, headers, body)
  inline def MethodNotAllowed(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.MethodNotAllowed, headers, body)
  inline def NotAcceptable(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.NotAcceptable, headers, body)
  inline def ProxyAuthenticationRequired(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.ProxyAuthenticationRequired, headers, body)
  inline def RequestTimeout(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.RequestTimeout, headers, body)
  inline def Conflict(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.Conflict, headers, body)
  inline def Gone(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.Gone, headers, body)
  inline def LengthRequired(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.LengthRequired, headers, body)
  inline def PreconditionFailed(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.PreconditionFailed, headers, body)
  inline def PayloadTooLarge(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.PayloadTooLarge, headers, body)
  inline def UriTooLong(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.UriTooLong, headers, body)
  inline def UnsupportedMediaType(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.UnsupportedMediaType, headers, body)
  inline def RangeNotSatisfiable(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.RangeNotSatisfiable, headers, body)
  inline def ExpectationFailed(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.ExpectationFailed, headers, body)
  inline def ImATeapot(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.ImATeapot, headers, body)
  inline def MisdirectedRequest(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.MisdirectedRequest, headers, body)
  inline def UnprocessableContent(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.UnprocessableContent, headers, body)
  inline def Locked(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.Locked, headers, body)
  inline def FailedDependency(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.FailedDependency, headers, body)
  inline def TooEarlyExperimental(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.TooEarlyExperimental, headers, body)
  inline def UpgradeRequired(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.UpgradeRequired, headers, body)
  inline def PreconditionRequired(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.PreconditionRequired, headers, body)
  inline def TooManyRequests(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.TooManyRequests, headers, body)
  inline def RequestHeaderFieldsTooLarge(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.RequestHeaderFieldsTooLarge, headers, body)
  inline def UnavailableForLegalReasons(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.UnavailableForLegalReasons, headers, body)
  inline def InternalServerError(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.InternalServerError, headers, body)
  inline def NotImplemented(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.NotImplemented, headers, body)
  inline def BadGateway(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.BadGateway, headers, body)
  inline def ServiceUnavailable(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.ServiceUnavailable, headers, body)
  inline def GatewayTimeout(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.GatewayTimeout, headers, body)
  inline def HttpVersionNotSupported(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.HttpVersionNotSupported, headers, body)
  inline def VariantAlsoNegotiates(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.VariantAlsoNegotiates, headers, body)
  inline def InsufficientStorage(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.InsufficientStorage, headers, body)
  inline def LoopDetected(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.LoopDetected, headers, body)
  inline def NotExtended(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.NotExtended, headers, body)
  inline def NetworkAuthenticationRequired(headers: Map[String, String] = Map.empty, body: String = "")(using Request): Response =
    mk(Status.NetworkAuthenticationRequired, headers, body)

  private def mk(status: Status, headers: Map[String, String], body: String)(using req: Request) =
    Response(
      version = req.version,
      status = status,
      headers = headers
        .updated(Constants.ContentLengthHeader, body.length.toString),
      body = stringToStream(body),
    )