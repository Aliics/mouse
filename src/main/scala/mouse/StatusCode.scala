package mouse

sealed trait StatusCode {
  def code: Int

  def text: String
}

object StatusCode {
  case object Continue extends StatusCode {
    def code = 100
    def text = "Continue"
  }
  case object SwitchingProtocols extends StatusCode {
    def code = 101
    def text = "Switching Protocols"
  }
  case object Processing extends StatusCode {
    def code = 102
    def text = "Processing"
  }
  case object EarlyHints extends StatusCode {
    def code = 103
    def text = "Early Hints"
  }

  case object Ok extends StatusCode {
    def code = 200
    def text = "OK"
  }
  case object Created extends StatusCode {
    def code = 201
    def text = "Created"
  }
  case object Accepted extends StatusCode {
    def code = 202
    def text = "Accepted"
  }
  case object NonAuthoritativeInformation extends StatusCode {
    def code = 203
    def text = "Non-Authoritative Information"
  }
  case object NoContent extends StatusCode {
    def code = 204
    def text = "No Content"
  }
  case object ResetContent extends StatusCode {
    def code = 205
    def text = "ResetContent"
  }
  case object PartialContent extends StatusCode {
    def code = 206
    def text = "Partial Content"
  }
  case object MultiStatus extends StatusCode {
    def code = 207
    def text = "Multi-Status"
  }
  case object AlreadyReported extends StatusCode {
    def code = 208
    def text = "Already Reported"
  }
  case object ImUsed extends StatusCode {
    def code = 226
    def text = "IM Used"
  }

  case object MultipleChoices extends StatusCode {
    def code = 300
    def text = "Multiple Choices"
  }
  case object MovedPermanently extends StatusCode {
    def code = 301
    def text = "Moved Permanently"
  }
  case object Found extends StatusCode {
    def code = 302
    def text = "Found"
  }
  case object SeeOther extends StatusCode {
    def code = 303
    def text = "See Other"
  }
  case object NotModified extends StatusCode {
    def code = 304
    def text = "Not Modified"
  }
  case object UseProxy extends StatusCode {
    def code = 305
    def text = "Use Proxy"
  }
  case object TemporaryRedirect extends StatusCode {
    def code = 307
    def text = "Temporary Redirect"
  }
  case object PermanentRedirect extends StatusCode {
    def code = 308
    def text = "Permanent Redirect"
  }

  case object BadRequest extends StatusCode {
    def code = 400
    def text = "Bad Request"
  }
  case object Unauthorized extends StatusCode {
    def code = 401
    def text = "Unauthorized"
  }
  case object PaymentRequired extends StatusCode {
    def code = 402
    def text = "Payment Required"
  }
  case object Forbidden extends StatusCode {
    def code = 403
    def text = "Forbidden"
  }
  case object NotFound extends StatusCode {
    def code = 404
    def text = "Not Found"
  }
  case object MethodNotAllowed extends StatusCode {
    def code = 405
    def text = "MethodNotAllowed"
  }
  case object NotAcceptable extends StatusCode {
    def code = 406
    def text = "Not Acceptable"
  }
  case object ProxyAuthenticationRequired extends StatusCode {
    def code = 407
    def text = "Proxy Authenticated Required"
  }
  case object RequestTimeout extends StatusCode {
    def code = 408
    def text = "Request Timeout"
  }
  case object Conflict extends StatusCode {
    def code = 409
    def text = "Conflict"
  }
  case object Gone extends StatusCode {
    def code = 410
    def text = "Gone"
  }
  case object LengthRequired extends StatusCode {
    def code = 411
    def text = "Length Required"
  }
  case object PreconditionFailed extends StatusCode {
    def code = 412
    def text = "Precondition Failed"
  }
  case object PayloadTooLarge extends StatusCode {
    def code = 413
    def text = "Payload Too Large"
  }
  case object UriTooLong extends StatusCode {
    def code = 414
    def text = "URI Too Long"
  }
  case object UnsupportedMediaType extends StatusCode {
    def code = 415
    def text = "Unsupported Media Type"
  }
  case object RangeNotSatisfiable extends StatusCode {
    def code = 416
    def text = "Range Not Satisfiable"
  }
  case object ExpectationFailed extends StatusCode {
    def code = 417
    def text = "Expectation Failed"
  }
  case object ImATeapot extends StatusCode {
    def code = 418
    def text = "I'm a teapot"
  }
  case object MisdirectedRequest extends StatusCode {
    def code = 421
    def text = "Misdirected Request"
  }
  case object UnprocessableContent extends StatusCode {
    def code = 422
    def text = "Unprocessable Content"
  }
  case object Locked extends StatusCode {
    def code = 423
    def text = "Locked"
  }
  case object FailedDependency extends StatusCode {
    def code = 424
    def text = "Failed Dependency"
  }
  case object TooEarly extends StatusCode {
    def code = 425
    def text = "Too Early"
  }
  case object UpgradeRequired extends StatusCode {
    def code = 426
    def text = "UpgradeRequired"
  }
  case object PreconditionRequired extends StatusCode {
    def code = 428
    def text = "Precondition Required"
  }
  case object TooManyRequests extends StatusCode {
    def code = 429
    def text = "Too Many Requests"
  }
  case object RequestHeaderFieldsTooLarge extends StatusCode {
    def code = 431
    def text = "Request Header Fields Too Large"
  }
  case object UnavailableForLegalReasons extends StatusCode {
    def code = 451
    def text = "UnavailableForLegalReasons"
  }

  case object InternalServerError extends StatusCode {
    def code = 500
    def text = "Internal Server Error"
  }
  case object NotImplemented extends StatusCode {
    def code = 501
    def text = "Not Implemented"
  }
  case object BadGateway extends StatusCode {
    def code = 502
    def text = "Bad Gateway"
  }
  case object ServiceUnavailable extends StatusCode {
    def code = 500
    def text = "Service Unavailable"
  }
  case object GatewayTimeout extends StatusCode {
    def code = 504
    def text = "Gateway Timeout"
  }
  case object HttpVersionNotSupported extends StatusCode {
    def code = 505
    def text = "HTTP Version Not Supported"
  }
  case object VariantAlsoNegotiates extends StatusCode {
    def code = 506
    def text = "Variant also Negotiates"
  }z
  case object InsufficientStorage extends StatusCode {
    def code = 507
    def text = "Insufficient Storage"
  }
  case object LoopDetected extends StatusCode {
    def code = 508
    def text = "Loop Detected"
  }
  case object NotExtended extends StatusCode {
    def code = 510
    def text = "Not Extended"
  }
  case object NetworkAuthenticationRequired extends StatusCode {
    def code = 511
    def text = "Network Authentication Required"
  }
}
