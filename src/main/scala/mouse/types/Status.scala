package mouse.types

import mouse.errors.ParseError

/**
 * Reference: [[https://developer.mozilla.org/en-US/docs/Web/HTTP/Status]]
 *
 * @param code Numeric status code
 */
enum Status(code: Int):
  case Continue extends Status(100)
  case SwitchingProtocols extends Status(101)
  case Processing extends Status(102)
  case EarlyHints extends Status(103)
  case Ok extends Status(200)
  case Created extends Status(201)
  case Accepted extends Status(202)
  case NonAuthoritativeInformation extends Status(203)
  case NoContent extends Status(204)
  case ResetContent extends Status(205)
  case PartialContent extends Status(206)
  case MultiStatus extends Status(207)
  case AlreadyReported extends Status(208)
  case ImUsed extends Status(226)
  case MultipleChoices extends Status(300)
  case MovedPermanently extends Status(301)
  case Found extends Status(302)
  case SeeOther extends Status(303)
  case NotModified extends Status(304)
  case UseProxyDeprecated extends Status(305)
  case TemporaryRedirect extends Status(307)
  case PermanentRedirect extends Status(308)
  case BadRequest extends Status(400)
  case Unauthorized extends Status(401)
  case PaymentRequiredExperimental extends Status(402)
  case Forbidden extends Status(403)
  case NotFound extends Status(404)
  case MethodNotAllowed extends Status(405)
  case NotAcceptable extends Status(406)
  case ProxyAuthenticationRequired extends Status(407)
  case RequestTimeout extends Status(408)
  case Conflict extends Status(409)
  case Gone extends Status(410)
  case LengthRequired extends Status(411)
  case PreconditionFailed extends Status(412)
  case PayloadTooLarge extends Status(413)
  case UriTooLong extends Status(414)
  case UnsupportedMediaType extends Status(415)
  case RangeNotSatisfiable extends Status(416)
  case ExpectationFailed extends Status(417)
  case ImATeapot extends Status(418)
  case MisdirectedRequest extends Status(421)
  case UnprocessableContent extends Status(422)
  case Locked extends Status(423)
  case FailedDependency extends Status(424)
  case TooEarlyExperimental extends Status(425)
  case UpgradeRequired extends Status(426)
  case PreconditionRequired extends Status(428)
  case TooManyRequests extends Status(429)
  case RequestHeaderFieldsTooLarge extends Status(431)
  case UnavailableForLegalReasons extends Status(451)
  case InternalServerError extends Status(500)
  case NotImplemented extends Status(501)
  case BadGateway extends Status(502)
  case ServiceUnavailable extends Status(503)
  case GatewayTimeout extends Status(504)
  case HttpVersionNotSupported extends Status(505)
  case VariantAlsoNegotiates extends Status(506)
  case InsufficientStorage extends Status(507)
  case LoopDetected extends Status(508)
  case NotExtended extends Status(510)
  case NetworkAuthenticationRequired extends Status(511)

  inline override def toString: String =
    s"$code ${
      this match
        case Continue => "Continue"
        case SwitchingProtocols => "Switching Protocols"
        case Processing => "Processing"
        case EarlyHints => "Early Hints"
        case Ok => "OK"
        case Created => "Created"
        case Accepted => "Accepted"
        case NonAuthoritativeInformation => "Non Authoritative Information"
        case NoContent => "No Content"
        case ResetContent => "Reset Content"
        case PartialContent => "Partial Content"
        case MultiStatus => "Multi Status"
        case AlreadyReported => "Already Reported"
        case ImUsed => "IMUsed"
        case MultipleChoices => "Multiple Choices"
        case MovedPermanently => "Moved Permanently"
        case Found => "Found"
        case SeeOther => "See Other"
        case NotModified => "Not Modified"
        case UseProxyDeprecated => "Use Proxy Deprecated"
        case TemporaryRedirect => "Temporary Redirect"
        case PermanentRedirect => "Permanent Redirect"
        case BadRequest => "Bad Request"
        case Unauthorized => "Unauthorized"
        case PaymentRequiredExperimental => "Payment Required Experimental"
        case Forbidden => "Forbidden"
        case NotFound => "Not Found"
        case MethodNotAllowed => "Method Not Allowed"
        case NotAcceptable => "Not Acceptable"
        case ProxyAuthenticationRequired => "Proxy Authentication Required"
        case RequestTimeout => "Request Timeout"
        case Conflict => "Conflict"
        case Gone => "Gone"
        case LengthRequired => "Length Required"
        case PreconditionFailed => "Precondition Failed"
        case PayloadTooLarge => "Payload Too Large"
        case UriTooLong => "URI Too Long"
        case UnsupportedMediaType => "Unsupported Media Type"
        case RangeNotSatisfiable => "Range Not Satisfiable"
        case ExpectationFailed => "Expectation Failed"
        case ImATeapot => "I'm a teapot"
        case MisdirectedRequest => "Misdirected Request"
        case UnprocessableContent => "Unprocessable Content"
        case Locked => "Locked"
        case FailedDependency => "Failed Dependency"
        case TooEarlyExperimental => "Too Early Experimental"
        case UpgradeRequired => "Upgrade Required"
        case PreconditionRequired => "Precondition Required"
        case TooManyRequests => "Too Many Requests"
        case RequestHeaderFieldsTooLarge => "Request Header Fields Too Large"
        case UnavailableForLegalReasons => "Unavailable For Legal Reasons"
        case InternalServerError => "Internal Server Error"
        case NotImplemented => "Not Implemented"
        case BadGateway => "Bad Gateway"
        case ServiceUnavailable => "Service Unavailable"
        case GatewayTimeout => "Gateway Timeout"
        case HttpVersionNotSupported => "HTTP Version Not Supported"
        case VariantAlsoNegotiates => "Variant Also Negotiates"
        case InsufficientStorage => "Insufficient Storage"
        case LoopDetected => "Loop Detected"
        case NotExtended => "Not Extended"
        case NetworkAuthenticationRequired => "Network Authentication Required"
    }"

object Status:
  /**
   * Parse an HTTP Status string.
   * @param s Status code (e.g. 200 OK)
   * @return [[Status]] or [[ParseError]] if malformed.
   */
  def apply(s: String): Either[ParseError, Status] =
    Status.values.find(_.toString == s) match
      case Some(status) => Right(status)
      case None => Left(ParseError(s"Invalid status: $s"))
