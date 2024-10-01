package mouse

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
    this match
      case Continue => "100 Continue"
      case SwitchingProtocols => "101 Switching Protocols"
      case Processing => "102 Processing"
      case EarlyHints => "103 Early Hints"
      case Ok => "200 OK"
      case Created => "201 Created"
      case Accepted => "202 Accepted"
      case NonAuthoritativeInformation => "203 Non Authoritative Information"
      case NoContent => "204 No Content"
      case ResetContent => "205 Reset Content"
      case PartialContent => "206 Partial Content"
      case MultiStatus => "207 Multi Status"
      case AlreadyReported => "208 Already Reported"
      case ImUsed => "226 IMUsed"
      case MultipleChoices => "300 Multiple Choices"
      case MovedPermanently => "301 Moved Permanently"
      case Found => "302 Found"
      case SeeOther => "303 See Other"
      case NotModified => "304 Not Modified"
      case UseProxyDeprecated => "305 Use Proxy Deprecated"
      case TemporaryRedirect => "307 Temporary Redirect"
      case PermanentRedirect => "308 Permanent Redirect"
      case BadRequest => "400 Bad Request"
      case Unauthorized => "401 Unauthorized"
      case PaymentRequiredExperimental => "402 Payment Required Experimental"
      case Forbidden => "403 Forbidden"
      case NotFound => "404 Not Found"
      case MethodNotAllowed => "405 Method Not Allowed"
      case NotAcceptable => "406 Not Acceptable"
      case ProxyAuthenticationRequired => "407 Proxy Authentication Required"
      case RequestTimeout => "408 Request Timeout"
      case Conflict => "409 Conflict"
      case Gone => "410 Gone"
      case LengthRequired => "411 Length Required"
      case PreconditionFailed => "412 Precondition Failed"
      case PayloadTooLarge => "413 Payload Too Large"
      case UriTooLong => "414 URI Too Long"
      case UnsupportedMediaType => "415 Unsupported Media Type"
      case RangeNotSatisfiable => "416 Range Not Satisfiable"
      case ExpectationFailed => "417 Expectation Failed"
      case ImATeapot => "418 I'm a teapot"
      case MisdirectedRequest => "421 Misdirected Request"
      case UnprocessableContent => "422 Unprocessable Content"
      case Locked => "423 Locked"
      case FailedDependency => "424 Failed Dependency"
      case TooEarlyExperimental => "425 Too Early Experimental"
      case UpgradeRequired => "426 Upgrade Required"
      case PreconditionRequired => "428 Precondition Required"
      case TooManyRequests => "429 Too Many Requests"
      case RequestHeaderFieldsTooLarge => "431 Request Header Fields Too Large"
      case UnavailableForLegalReasons => "451 Unavailable For Legal Reasons"
      case InternalServerError => "500 Internal Server Error"
      case NotImplemented => "501 Not Implemented"
      case BadGateway => "502 Bad Gateway"
      case ServiceUnavailable => "503 Service Unavailable"
      case GatewayTimeout => "504 Gateway Timeout"
      case HttpVersionNotSupported => "505 HTTP Version Not Supported"
      case VariantAlsoNegotiates => "506 Variant Also Negotiates"
      case InsufficientStorage => "507 Insufficient Storage"
      case LoopDetected => "508 Loop Detected"
      case NotExtended => "510 Not Extended"
      case NetworkAuthenticationRequired => "511 Network Authentication Required"
