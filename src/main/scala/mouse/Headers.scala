package mouse

object Headers {
  def apply(): Headers = Map()

  val Accept: String = "Accept"
  val AcceptCH: String = "Accept-CH"
  val AcceptCHLifetime: String = "Accept-CH-Lifetime"
  val AcceptCharset: String = "Accept-Charset"
  val AcceptEncoding: String = "Accept-Encoding"
  val AcceptLanguage: String = "Accept-Language"
  val AcceptPatch: String = "Accept-Patch"
  val AcceptPost: String = "Accept-Post"
  val AcceptRanges: String = "Accept-Ranges"
  val AccessControlAllowCredentials: String = "Access-Control-Allow-Credentials"
  val AccessControlAllowHeaders: String = "Access-Control-Allow-Headers"
  val AccessControlAllowMethods: String = "Access-Control-Allow-Methods"
  val AccessControlAllowOrigin: String = "Access-Control-Allow-Origin"
  val AccessControlExposeHeaders: String = "Access-Control-Expose-Headers"
  val AccessControlMaxAge: String = "Access-Control-Max-Age"
  val AccessControlRequestHeaders: String = "Access-Control-Request-Headers"
  val AccessControlRequestMethod: String = "Access-Control-Request-Method"
  val Age: String = "Age"
  val Allow: String = "Allow"
  val AltSvc: String = "Alt-Svc"
  val AltUsed: String = "Alt-Used"
  val Authorization: String = "Authorization"
  val CacheControl: String = "Cache-Control"
  val ClearSiteData: String = "Clear-Site-Data"
  val Connection: String = "Connection"
  val ContentDisposition: String = "Content-Disposition"
  val ContentDPR: String = "Content-DPR"
  val ContentEncoding: String = "Content-Encoding"
  val ContentLanguage: String = "Content-Language"
  val ContentLength: String = "Content-Length"
  val ContentLocation: String = "Content-Location"
  val ContentRange: String = "Content-Range"
  val ContentSecurityPolicy: String = "Content-Security-Policy"
  val ContentSecurityPolicyReportOnly: String = "Content-Security-Policy-Report-Only"
  val ContentType: String = "Content-Type"
  val Cookie: String = "Cookie"
  val CriticalCH: String = "Critical-CH"
  val CrossOriginEmbedderPolicy: String = "Cross-Origin-Embedder-Policy"
  val CrossOriginOpenerPolicy: String = "Cross-Origin-Opener-Policy"
  val CrossOriginResourcePolicy: String = "Cross-Origin-Resource-Policy"
  val Date: String = "Date"
  val DeviceMemory: String = "Device-Memory"
  val Digest: String = "Digest"
  val DNT: String = "DNT"
  val Downlink: String = "Downlink"
  val DPR: String = "DPR"
  val EarlyData: String = "Early-Data"
  val ECT: String = "ECT"
  val ETag: String = "ETag"
  val Expect: String = "Expect"
  val ExpectCT: String = "Expect-CT"
  val Expires: String = "Expires"
  val Forwarded: String = "Forwarded"
  val From: String = "From"
  val Host: String = "Host"
  val IfMatch: String = "If-Match"
  val IfModifiedSince: String = "If-Modified-Since"
  val IfNoneMatch: String = "If-None-Match"
  val IfRange: String = "If-Range"
  val IfUnmodifiedSince: String = "If-Unmodified-Since"
  val KeepAlive: String = "Keep-Alive"
  val LargeAllocation: String = "Large-Allocation"
  val LastModified: String = "Last-Modified"
  val Link: String = "Link"
  val Location: String = "Location"
  val MaxForwards: String = "Max-Forwards"
  val NEL: String = "NEL"
  val Origin: String = "Origin"
  val PermissionsPolicy: String = "Permissions-Policy"
  val Pragma: String = "Pragma"
  val ProxyAuthenticate: String = "Proxy-Authenticate"
  val ProxyAuthorization: String = "Proxy-Authorization"
  val Range: String = "Range"
  val Referer: String = "Referer"
  val ReferrerPolicy: String = "Referrer-Policy"
  val RetryAfter: String = "Retry-After"
  val RTT: String = "RTT"
  val SaveData: String = "Save-Data"
  val SecCHPrefersColorScheme: String = "Sec-CH-Prefers-Color-Scheme"
  val SecCHPrefersReducedMotion: String = "Sec-CH-Prefers-Reduced-Motion"
  val SecCHPrefersReducedTransparency: String = "Sec-CH-Prefers-Reduced-Transparency"
  val SecCHUA: String = "Sec-CH-UA"
  val SecCHUAArch: String = "Sec-CH-UA-Arch"
  val SecCHUABitness: String = "Sec-CH-UA-Bitness"
  val SecCHUAFullVersion: String = "Sec-CH-UA-Full-Version"
  val SecCHUAFullVersionList: String = "Sec-CH-UA-Full-Version-List"
  val SecCHUAMobile: String = "Sec-CH-UA-Mobile"
  val SecCHUAModel: String = "Sec-CH-UA-Model"
  val SecCHUAPlatform: String = "Sec-CH-UA-Platform"
  val SecCHUAPlatformVersion: String = "Sec-CH-UA-Platform-Version"
  val SecFetchDest: String = "Sec-Fetch-Dest"
  val SecFetchMode: String = "Sec-Fetch-Mode"
  val SecFetchSite: String = "Sec-Fetch-Site"
  val SecFetchUser: String = "Sec-Fetch-User"
  val SecGPC: String = "Sec-GPC"
  val SecPurpose: String = "Sec-Purpose"
  val SecWebSocketAccept: String = "Sec-WebSocket-Accept"
  val Server: String = "Server"
  val ServerTiming: String = "Server-Timing"
  val ServiceWorkerNavigationPreload: String = "Service-Worker-Navigation-Preload"
  val SetCookie: String = "Set-Cookie"
  val SourceMap: String = "SourceMap"
  val StrictTransportSecurity: String = "Strict-Transport-Security"
  val SupportsLoadingMode: String = "Supports-Loading-Mode"
  val TE: String = "TE"
  val TimingAllowOrigin: String = "Timing-Allow-Origin"
  val Tk: String = "Tk"
  val Trailer: String = "Trailer"
  val TransferEncoding: String = "Transfer-Encoding"
  val Upgrade: String = "Upgrade"
  val UpgradeInsecureRequests: String = "Upgrade-Insecure-Requests"
  val UserAgent: String = "User-Agent"
  val Vary: String = "Vary"
  val Via: String = "Via"
  val ViewportWidth: String = "Viewport-Width"
  val WantDigest: String = "Want-Digest"
  val Warning: String = "Warning"
  val Width: String = "Width"
  val WWWAuthenticate: String = "WWW-Authenticate"
  val XContentTypeOptions: String = "X-Content-Type-Options"
  val XDNSPrefetchControl: String = "X-DNS-Prefetch-Control"
  val XForwardedFor: String = "X-Forwarded-For"
  val XForwardedHost: String = "X-Forwarded-Host"
  val XForwardedProto: String = "X-Forwarded-Proto"
  val XFrameOptions: String = "X-Frame-Options"
  val XXSSProtection: String = "X-XSS-Protection"
}
