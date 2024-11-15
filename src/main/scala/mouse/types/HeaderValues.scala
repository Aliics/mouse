package mouse.types

/**
 * Headers wrapper to extract the raw header values and utilize the MDN defined header keys.
 *
 * Reference: [[https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers]]
 * @param headers Raw header map
 */
case class HeaderValues(headers: Map[String, String]):
  private def findHeaderValue(header: String) =
    headers.find((k, _) => k `equalsIgnoreCase` header).map(_._2)

  /**
   * Defines the authentication method that should be used to access a resource.
   */
  lazy val wwwAuthenticate: Option[String] = findHeaderValue(Headers.WwwAuthenticate)

  /**
   * Contains the credentials to authenticate a user-agent with a server.
   */
  lazy val authorization: Option[String] = findHeaderValue(Headers.Authorization)

  /**
   * Defines the authentication method that should be used to access a resource behind a proxy server.
   */
  lazy val proxyAuthenticate: Option[String] = findHeaderValue(Headers.ProxyAuthenticate)

  /**
   * Contains the credentials to authenticate a user agent with a proxy server.
   */
  lazy val proxyAuthorization: Option[String] = findHeaderValue(Headers.ProxyAuthorization)

  /**
   * The time, in seconds, that the object has been in a proxy cache.
   */
  lazy val age: Option[String] = findHeaderValue(Headers.Age)

  /**
   * Directives for caching mechanisms in both requests and responses.
   */
  lazy val cacheControl: Option[String] = findHeaderValue(Headers.CacheControl)

  /**
   * Clears browsing data (e.g. cookies, storage, cache) associated with the requesting website.
   */
  lazy val clearSiteData: Option[String] = findHeaderValue(Headers.ClearSiteData)

  /**
   * The date/time after which the response is considered stale.
   */
  lazy val expires: Option[String] = findHeaderValue(Headers.Expires)

  /**
   * Specifies a set of rules that define how a URL's query parameters will affect cache matching. These rules dictate
   * whether the same URL with different URL parameters should be saved as separate browser cache entries.
   */
  lazy val noVarySearch: Option[String] = findHeaderValue(Headers.NoVarySearch)

  /**
   * The last modification date of the resource, used to compare several versions of the same resource. It is less
   * accurate than ETag, but easier to calculate in some environments. Conditional requests using If-Modified-Since and
   * If-Unmodified-Since use this value to change the behavior of the request.
   */
  lazy val lastModified: Option[String] = findHeaderValue(Headers.LastModified)

  /**
   * A unique string identifying the version of the resource. Conditional requests using If-Match and If-None-Match use
   * this value to change the behavior of the request.
   */
  lazy val eTag: Option[String] = findHeaderValue(Headers.ETag)

  /**
   * Makes the request conditional, and applies the method only if the stored resource matches one of the given ETags.
   */
  lazy val ifMatch: Option[String] = findHeaderValue(Headers.IfMatch)

  /**
   * Makes the request conditional, and applies the method only if the stored resource doesn't match any of the given
   * ETags. This is used to update caches (for safe requests), or to prevent uploading a new resource when one already
   * exists.
   */
  lazy val ifNoneMatch: Option[String] = findHeaderValue(Headers.IfNoneMatch)

  /**
   * Makes the request conditional, and expects the resource to be transmitted only if it has been modified after the
   * given date. This is used to transmit data only when the cache is out of date.
   */
  lazy val ifModifiedSince: Option[String] = findHeaderValue(Headers.IfModifiedSince)

  /**
   * Makes the request conditional, and expects the resource to be transmitted only if it has not been modified after
   * the given date. This ensures the coherence of a new fragment of a specific range with previous ones, or to
   * implement an optimistic concurrency control system when modifying existing documents.
   */
  lazy val ifUnmodifiedSince: Option[String] = findHeaderValue(Headers.IfUnmodifiedSince)

  /**
   * Determines how to match request headers to decide whether a cached response can be used rather than requesting a
   * fresh one from the origin server.
   */
  lazy val vary: Option[String] = findHeaderValue(Headers.Vary)

  /**
   * Controls whether the network connection stays open after the current transaction finishes.
   */
  lazy val connection: Option[String] = findHeaderValue(Headers.Connection)

  /**
   * Controls how long a persistent connection should stay open.
   */
  lazy val keepAlive: Option[String] = findHeaderValue(Headers.KeepAlive)

  /**
   * Informs the server about the types of data that can be sent back.
   */
  lazy val accept: Option[String] = findHeaderValue(Headers.Accept)

  /**
   * Advertises a client's supported character encodings. It is deprecated because UTF-8 has become ubiquitous and use
   * of the header makes client fingerprinting easier.
   */
  lazy val acceptCharset: Option[String] = findHeaderValue(Headers.AcceptCharset)

  /**
   * The encoding algorithm, usually a compression algorithm, that can be used on the resource sent back.
   */
  lazy val acceptEncoding: Option[String] = findHeaderValue(Headers.AcceptEncoding)

  /**
   * Informs the server about the human language the server is expected to send back. This is a hint and is not
   * necessarily under the full control of the user: the server should always pay attention not to override an explicit
   * user choice (like selecting a language from a dropdown).
   */
  lazy val acceptLanguage: Option[String] = findHeaderValue(Headers.AcceptLanguage)

  /**
   * A request content negotiation response header that advertises which media type the server is able to understand in
   * a PATCH request.
   */
  lazy val acceptPatch: Option[String] = findHeaderValue(Headers.AcceptPatch)

  /**
   * A request content negotiation response header that advertises which media type the server is able to understand in
   * a POST request.
   */
  lazy val acceptPost: Option[String] = findHeaderValue(Headers.AcceptPost)

  /**
   * Indicates expectations that need to be fulfilled by the server to properly handle the request.
   */
  lazy val expect: Option[String] = findHeaderValue(Headers.Expect)

  /**
   * When using TRACE, indicates the maximum number of hops the request can do before being reflected to the sender.
   */
  lazy val maxForwards: Option[String] = findHeaderValue(Headers.MaxForwards)

  /**
   * Contains stored HTTP cookies previously sent by the server with the Set-Cookie header.
   */
  lazy val cookie: Option[String] = findHeaderValue(Headers.Cookie)

  /**
   * Send cookies from the server to the user-agent.
   */
  lazy val setCookie: Option[String] = findHeaderValue(Headers.SetCookie)

  /**
   * Indicates whether the response to the request can be exposed when the credentials flag is true.
   */
  lazy val accessControlAllowCredentials: Option[String] = findHeaderValue(Headers.AccessControlAllowCredentials)

  /**
   * Used in response to a preflight request to indicate which HTTP headers can be used when making the actual request.
   */
  lazy val accessControlAllowHeaders: Option[String] = findHeaderValue(Headers.AccessControlAllowHeaders)

  /**
   * Specifies the methods allowed when accessing the resource in response to a preflight request.
   */
  lazy val accessControlAllowMethods: Option[String] = findHeaderValue(Headers.AccessControlAllowMethods)

  /**
   * Indicates whether the response can be shared.
   */
  lazy val accessControlAllowOrigin: Option[String] = findHeaderValue(Headers.AccessControlAllowOrigin)

  /**
   * Indicates which headers can be exposed as part of the response by listing their names.
   */
  lazy val accessControlExposeHeaders: Option[String] = findHeaderValue(Headers.AccessControlExposeHeaders)

  /**
   * Indicates how long the results of a preflight request can be cached.
   */
  lazy val accessControlMaxAge: Option[String] = findHeaderValue(Headers.AccessControlMaxAge)

  /**
   * Used when issuing a preflight request to let the server know which HTTP headers will be used when the actual
   * request is made.
   */
  lazy val accessControlRequestHeaders: Option[String] = findHeaderValue(Headers.AccessControlRequestHeaders)

  /**
   * Used when issuing a preflight request to let the server know which HTTP method will be used when the actual
   * request is made.
   */
  lazy val accessControlRequestMethod: Option[String] = findHeaderValue(Headers.AccessControlRequestMethod)

  /**
   * Indicates where a fetch originates from.
   */
  lazy val origin: Option[String] = findHeaderValue(Headers.Origin)

  /**
   * Specifies origins that are allowed to see values of attributes retrieved via features of the Resource Timing API,
   * which would otherwise be reported as zero due to cross-origin restrictions.
   */
  lazy val timingAllowOrigin: Option[String] = findHeaderValue(Headers.TimingAllowOrigin)

  /**
   * Indicates if the resource transmitted should be displayed inline (default behavior without the header), or if it
   * should be handled like a download and the browser should present a "Save As" dialog.
   */
  lazy val contentDisposition: Option[String] = findHeaderValue(Headers.ContentDisposition)

  /**
   * Provides a digest of the stream of octets framed in an HTTP message (the message content) dependent on
   * Content-Encoding and Content-Range.
   */
  lazy val contentDigest: Option[String] = findHeaderValue(Headers.ContentDigest)

  /**
   * Provides a digest of the a resource. See Content-Digest and Repr-Digest.
   */
  lazy val digest: Option[String] = findHeaderValue(Headers.Digest)

  /**
   * Provides a digest of the selected representation of the target resource before transmission. Unlike the
   * Content-Digest, the digest does not consider Content-Encoding or Content-Range.
   */
  lazy val reprDigest: Option[String] = findHeaderValue(Headers.ReprDigest)

  /**
   * States the wish for a Content-Digest header. It is the Content- analogue of Want-Repr-Digest.
   */
  lazy val wantContentDigest: Option[String] = findHeaderValue(Headers.WantContentDigest)

  /**
   * States the wish for a Digest header. See Want-Content-Digest and Want-Repr-Digest instead.
   */
  lazy val wantDigest: Option[String] = findHeaderValue(Headers.WantDigest)

  /**
   * States the wish for a Repr-Digest header. It is the Repr- analogue of Want-Content-Digest.
   */
  lazy val wantReprDigest: Option[String] = findHeaderValue(Headers.WantReprDigest)

  /**
   * The size of the resource, in decimal number of bytes.
   */
  lazy val contentLength: Option[String] = findHeaderValue(Headers.ContentLength)

  /**
   * Indicates the media type of the resource.
   */
  lazy val contentType: Option[String] = findHeaderValue(Headers.ContentType)

  /**
   * Used to specify the compression algorithm.
   */
  lazy val contentEncoding: Option[String] = findHeaderValue(Headers.ContentEncoding)

  /**
   * Describes the human language(s) intended for the audience, so that it allows a user to differentiate according to
   * the users' own preferred language.
   */
  lazy val contentLanguage: Option[String] = findHeaderValue(Headers.ContentLanguage)

  /**
   * Indicates an alternate location for the returned data.
   */
  lazy val contentLocation: Option[String] = findHeaderValue(Headers.ContentLocation)

  /**
   * Contains information from the client-facing side of proxy servers that is altered or lost when a proxy is involved
   * in the path of the request.
   */
  lazy val forwarded: Option[String] = findHeaderValue(Headers.Forwarded)

  /**
   * Added by proxies, both forward and reverse proxies, and can appear in the request headers and the response headers.
   */
  lazy val via: Option[String] = findHeaderValue(Headers.Via)

  /**
   * Indicates if the server supports range requests, and if so in which unit the range can be expressed.
   */
  lazy val acceptRanges: Option[String] = findHeaderValue(Headers.AcceptRanges)

  /**
   * Indicates the part of a document that the server should return.
   */
  lazy val range: Option[String] = findHeaderValue(Headers.Range)

  /**
   * Creates a conditional range request that is only fulfilled if the given etag or date matches the remote resource.
   * Used to prevent downloading two ranges from incompatible version of the resource.
   */
  lazy val ifRange: Option[String] = findHeaderValue(Headers.IfRange)

  /**
   * Indicates where in a full body message a partial message belongs.
   */
  lazy val contentRange: Option[String] = findHeaderValue(Headers.ContentRange)

  /**
   * Indicates the URL to redirect a page to.
   */
  lazy val location: Option[String] = findHeaderValue(Headers.Location)

  /**
   * Directs the browser to reload the page or redirect to another. Takes the same value as the meta element with
   * http-equiv="refresh".
   */
  lazy val refresh: Option[String] = findHeaderValue(Headers.Refresh)

  /**
   * Contains an Internet email address for a human user who controls the requesting user agent.
   */
  lazy val from: Option[String] = findHeaderValue(Headers.From)

  /**
   * Specifies the domain name of the server (for virtual hosting), and (optionally) the TCP port number on which
   * the server is listening.
   */
  lazy val host: Option[String] = findHeaderValue(Headers.Host)

  /**
   * The address of the previous web page from which a link to the currently requested page was followed.
   */
  lazy val referer: Option[String] = findHeaderValue(Headers.Referer)

  /**
   * Governs which referrer information sent in the Referer header should be included with requests made.
   */
  lazy val referrerPolicy: Option[String] = findHeaderValue(Headers.ReferrerPolicy)

  /**
   * Contains a characteristic string that allows the network protocol peers to identify the application type, operating
   * system, software vendor or software version of the requesting software user agent.
   */
  lazy val userAgent: Option[String] = findHeaderValue(Headers.UserAgent)

  /**
   * Lists the set of HTTP request methods supported by a resource.
   */
  lazy val allow: Option[String] = findHeaderValue(Headers.Allow)

  /**
   * Contains information about the software used by the origin server to handle the request.
   */
  lazy val server: Option[String] = findHeaderValue(Headers.Server)

  /**
   * Allows a server to declare an embedder policy for a given document.
   */
  lazy val crossOriginEmbedderPolicy: Option[String] = findHeaderValue(Headers.CrossOriginEmbedderPolicy)

  /**
   * Prevents other domains from opening/controlling a window.
   */
  lazy val crossOriginOpenerPolicy: Option[String] = findHeaderValue(Headers.CrossOriginOpenerPolicy)

  /**
   * Prevents other domains from reading the response of the resources to which this header is applied. See also CORP
   * explainer article.
   */
  lazy val crossOriginResourcePolicy: Option[String] = findHeaderValue(Headers.CrossOriginResourcePolicy)

  /**
   * Controls resources the user agent is allowed to load for a given page.
   */
  lazy val contentSecurityPolicy: Option[String] = findHeaderValue(Headers.ContentSecurityPolicy)

  /**
   * Allows web developers to experiment with policies by monitoring, but not enforcing, their effects. These violation
   * reports consist of JSON documents sent via an HTTP POST request to the specified URI.
   */
  lazy val contentSecurityPolicyReportOnly: Option[String] = findHeaderValue(Headers.ContentSecurityPolicyReportOnly)

  /**
   * Lets sites opt in to reporting and enforcement of Certificate Transparency to detect use of misissued certificates
   * for that site.
   */
  lazy val expectCt: Option[String] = findHeaderValue(Headers.ExpectCt)

  /**
   * Provides a mechanism to allow and deny the use of browser features in a website's own frame, and in iframes that it
   * embeds.
   */
  lazy val permissionsPolicy: Option[String] = findHeaderValue(Headers.PermissionsPolicy)

  /**
   * Force communication using HTTPS instead of HTTP.
   */
  lazy val strictTransportSecurity: Option[String] = findHeaderValue(Headers.StrictTransportSecurity)

  /**
   * Sends a signal to the server expressing the client's preference for an encrypted and authenticated response, and
   * that it can successfully handle the upgrade-insecure-requests directive.
   */
  lazy val upgradeInsecureRequests: Option[String] = findHeaderValue(Headers.UpgradeInsecureRequests)

  /**
   * Disables MIME sniffing and forces browser to use the type given in Content-Type.
   */
  lazy val xContentTypeOptions: Option[String] = findHeaderValue(Headers.XContentTypeOptions)

  /**
   * Indicates whether a browser should be allowed to render a page in a frame, iframe, embed or object.
   */
  lazy val xFrameOptions: Option[String] = findHeaderValue(Headers.XFrameOptions)

  /**
   * Specifies if a cross-domain policy file (crossdomain.xml) is allowed. The file may define a policy to grant
   * clients, such as Adobe's Flash Player (now obsolete), Adobe Acrobat, Microsoft Silverlight (now obsolete),
   * or Apache Flex, permission to handle data across domains that would otherwise be restricted due to the Same-Origin
   * Policy. See the Cross-domain Policy File Specification for more information.
   */
  lazy val xPermittedCrossDomainPolicies: Option[String] = findHeaderValue(Headers.XPermittedCrossDomainPolicies)

  /**
   * May be set by hosting environments or other frameworks and contains information about them while not providing any
   * usefulness to the application or its visitors. Unset this header to avoid exposing potential vulnerabilities.
   */
  lazy val xPoweredBy: Option[String] = findHeaderValue(Headers.XPoweredBy)

  /**
   * Enables cross-site scripting filtering.
   */
  lazy val xXssProtection: Option[String] = findHeaderValue(Headers.XXssProtection)

  /**
   * Indicates the relationship between a request initiator's origin and its target's origin. It is a Structured Header
   * whose value is a token with possible values cross-site, same-origin, same-site, and none.
   */
  lazy val secFetchSite: Option[String] = findHeaderValue(Headers.SecFetchSite)

  /**
   * Indicates the request's mode to a server. It is a Structured Header whose value is a token with possible values
   * cors, navigate, no-cors, same-origin, and websocket.
   */
  lazy val secFetchMode: Option[String] = findHeaderValue(Headers.SecFetchMode)

  /**
   * Indicates whether or not a navigation request was triggered by user activation. It is a Structured Header whose
   * value is a boolean so possible values are ?0 for false and ?1 for true.
   */
  lazy val secFetchUser: Option[String] = findHeaderValue(Headers.SecFetchUser)

  /**
   * Indicates the request's destination. It is a Structured Header whose value is a token with possible values audio,
   * audioworklet, document, embed, empty, font, image, manifest, object, paintworklet, report, script, serviceworker,
   * sharedworker, style, track, video, worker, and xslt.
   */
  lazy val secFetchDest: Option[String] = findHeaderValue(Headers.SecFetchDest)

  /**
   * Indicates the purpose of the request, when the purpose is something other than immediate use by the user-agent.
   * The header currently has one possible value, prefetch, which indicates that the resource is being fetched
   * preemptively for a possible future navigation.
   */
  lazy val secPurpose: Option[String] = findHeaderValue(Headers.SecPurpose)

  /**
   * A request header sent in preemptive request to fetch() a resource during service worker boot. The value, which is
   * set with NavigationPreloadManager.setHeaderValue(), can be used to inform a server that a different resource
   * should be returned than in a normal fetch() operation.
   */
  lazy val serviceWorkerNavigationPreload: Option[String] = findHeaderValue(Headers.ServiceWorkerNavigationPreload)

  /**
   * Response header used to specify server endpoints where the browser should send warning and error reports when
   * using the Reporting API.
   */
  lazy val reportingEndpoints: Option[String] = findHeaderValue(Headers.ReportingEndpoints)

  /**
   * Response header used to specify server endpoints where the browser should send warning and error reports when
   * using the Reporting API.
   */
  lazy val reportTo: Option[String] = findHeaderValue(Headers.ReportTo)

  /**
   * Specifies the form of encoding used to safely transfer the resource to the user.
   */
  lazy val transferEncoding: Option[String] = findHeaderValue(Headers.TransferEncoding)

  /**
   * Specifies the transfer encodings the user agent is willing to accept.
   */
  lazy val tE: Option[String] = findHeaderValue(Headers.Te)

  /**
   * Allows the sender to include additional fields at the end of chunked message.
   */
  lazy val trailer: Option[String] = findHeaderValue(Headers.Trailer)

  /**
   * Response header that indicates that the server is willing to upgrade to a WebSocket connection.
   */
  lazy val secWebSocketAccept: Option[String] = findHeaderValue(Headers.SecWebSocketAccept)

  /**
   * In requests, this header indicates the WebSocket extensions supported by the client in preferred order. In
   * responses, it indicates the extension selected by the server from the client's preferences.
   */
  lazy val secWebSocketExtensions: Option[String] = findHeaderValue(Headers.SecWebSocketExtensions)

  /**
   * Request header containing a key that verifies that the client explicitly intends to open a WebSocket.
   */
  lazy val secWebSocketKey: Option[String] = findHeaderValue(Headers.SecWebSocketKey)

  /**
   * In requests, this header indicates the sub-protocols supported by the client in preferred order. In responses, it
   * indicates the the sub-protocol selected by the server from the client's preferences.
   */
  lazy val secWebSocketProtocol: Option[String] = findHeaderValue(Headers.SecWebSocketProtocol)

  /**
   * In requests, this header indicates the version of the WebSocket protocol used by the client. In responses, it is
   * sent only if the requested protocol version is not supported by the server, and lists the versions that the server
   * supports.
   */
  lazy val secWebSocketVersion: Option[String] = findHeaderValue(Headers.SecWebSocketVersion)

  /**
   * Used to list alternate ways to reach this service.
   */
  lazy val altSvc: Option[String] = findHeaderValue(Headers.AltSvc)

  /**
   * Used to identify the alternative service in use.
   */
  lazy val altUsed: Option[String] = findHeaderValue(Headers.AltUsed)

  /**
   * Contains the date and time at which the message was originated.
   */
  lazy val date: Option[String] = findHeaderValue(Headers.Date)

  /**
   * This entity-header field provides a means for serializing one or more links in HTTP headers. It is semantically
   * equivalent to the HTML link element.
   */
  lazy val link: Option[String] = findHeaderValue(Headers.Link)

  /**
   * Indicates how long the user agent should wait before making a follow-up request.
   */
  lazy val retryAfter: Option[String] = findHeaderValue(Headers.RetryAfter)

  /**
   * Communicates one or more metrics and descriptions for the given request-response cycle.
   */
  lazy val serverTiming: Option[String] = findHeaderValue(Headers.ServerTiming)

  /**
   * Used to remove the path restriction by including this header in the response of the Service Worker script.
   */
  lazy val serviceWorkerAllowed: Option[String] = findHeaderValue(Headers.ServiceWorkerAllowed)

  /**
   * Links generated code to a source map.
   */
  lazy val sourceMap: Option[String] = findHeaderValue(Headers.SourceMap)

  /**
   * This HTTP/1.1 (only) header can be used to upgrade an already established client/server connection to a different
   * protocol (over the same transport protocol). For example, it can be used by a client to upgrade a connection from
   * HTTP 1.1 to HTTP 2.0, or an HTTP or HTTPS connection into a WebSocket.
   */
  lazy val upgrade: Option[String] = findHeaderValue(Headers.Upgrade)

  /**
   * Provides a hint from about the priority of a particular resource request on a particular connection. The value can
   * be sent in a request to indicate the client priority, or in a response if the server chooses to reprioritize the
   * request.
   */
  lazy val priority: Option[String] = findHeaderValue(Headers.Priority)

  /**
   * Used to indicate that the response corresponding to the current request is eligible to take part in attribution
   * reporting, by registering either an attribution source or trigger.
   */
  lazy val attributionReportingEligible: Option[String] = findHeaderValue(Headers.AttributionReportingEligible)

  /**
   * Included as part of a response to a request that included an Attribution-Reporting-Eligible header, this is used
   * to register an attribution source.
   */
  lazy val attributionReportingRegisterSource: Option[String] = findHeaderValue(Headers.AttributionReportingRegisterSource)

  /**
   * Included as part of a response to a request that included an Attribution-Reporting-Eligible header, this is used
   * to register an attribution trigger.
   */
  lazy val attributionReportingRegisterTrigger: Option[String] = findHeaderValue(Headers.AttributionReportingRegisterTrigger)

  /**
   * Servers can advertise support for Client Hints using the Accept-CH header field or an equivalent HTML meta element
   * with http-equiv attribute.
   */
  lazy val acceptCh: Option[String] = findHeaderValue(Headers.AcceptCh)

  /**
   * Servers use Critical-CH along with Accept-CH to specify that accepted client hints are also critical client hints.
   */
  lazy val criticalCh: Option[String] = findHeaderValue(Headers.CriticalCh)

  /**
   * User agent's branding and version.
   */
  lazy val secChUa: Option[String] = findHeaderValue(Headers.SecChUa)

  /**
   * User agent's underlying platform architecture.
   */
  lazy val secChUaArch: Option[String] = findHeaderValue(Headers.SecChUaArch)

  /**
   * User agent's underlying CPU architecture bitness (for example "64" bit).
   */
  lazy val secChUaBitness: Option[String] = findHeaderValue(Headers.SecChUaBitness)

  /**
   * User agent's form-factors, describing how the user interacts with the user-agent.
   */
  lazy val secChUaFormFactor: Option[String] = findHeaderValue(Headers.SecChUaFormFactor)

  /**
   * User agent's full version string.
   */
  lazy val secChUaFullVersion: Option[String] = findHeaderValue(Headers.SecChUaFullVersion)

  /**
   * Full version for each brand in the user agent's brand list.
   */
  lazy val secChUaFullVersionList: Option[String] = findHeaderValue(Headers.SecChUaFullVersionList)

  /**
   * User agent is running on a mobile device or, more generally, prefers a "mobile" user experience.
   */
  lazy val secChUaMobile: Option[String] = findHeaderValue(Headers.SecChUaMobile)

  /**
   * User agent's device model.
   */
  lazy val secChUaModel: Option[String] = findHeaderValue(Headers.SecChUaModel)

  /**
   * User agent's underlying operation system/platform.
   */
  lazy val secChUaPlatform: Option[String] = findHeaderValue(Headers.SecChUaPlatform)

  /**
   * User agent's underlying operation system version.
   */
  lazy val secChUaPlatformVersion: Option[String] = findHeaderValue(Headers.SecChUaPlatformVersion)

  /**
   * Whether or not the user agent binary is running in 32-bit mode on 64-bit Windows.
   */
  lazy val secChUaWoW64: Option[String] = findHeaderValue(Headers.SecChUaWoW64)

  /**
   * User's preference of dark or light color scheme.
   */
  lazy val secChPrefersColorScheme: Option[String] = findHeaderValue(Headers.SecChPrefersColorScheme)

  /**
   * User's preference to see fewer animations and content layout shifts.
   */
  lazy val secChPrefersReducedMotion: Option[String] = findHeaderValue(Headers.SecChPrefersReducedMotion)

  /**
   * Request header indicates the user agent's preference for reduced transparency.
   */
  lazy val secChPrefersReducedTransparency: Option[String] = findHeaderValue(Headers.SecChPrefersReducedTransparency)

  /**
   * Response header used to confirm the image device to pixel ratio (DPR) in requests where the screen DPR client hint
   * was used to select an image resource.
   */
  lazy val contentDpr: Option[String] = findHeaderValue(Headers.ContentDpr)

  /**
   * Approximate amount of available client RAM memory. This is part of the Device Memory API.
   */
  lazy val deviceMemory: Option[String] = findHeaderValue(Headers.DeviceMemory)

  /**
   * Request header that provides the client device pixel ratio (the number of physical device pixels for each CSS pixel).
   */
  lazy val dpr: Option[String] = findHeaderValue(Headers.Dpr)

  /**
   * Request header provides the client's layout viewport width in CSS pixels.
   */
  lazy val viewportWidth: Option[String] = findHeaderValue(Headers.ViewportWidth)

  /**
   * Request header indicates the desired resource width in physical pixels (the intrinsic size of an image).
   */
  lazy val width: Option[String] = findHeaderValue(Headers.Width)

  /**
   * Approximate bandwidth of the client's connection to the server, in Mbps. This is part of the Network Information API.
   */
  lazy val downlink: Option[String] = findHeaderValue(Headers.Downlink)

  /**
   * The effective connection type ("network profile") that best matches the connection's latency and bandwidth. This
   * is part of the Network Information API.
   */
  lazy val ect: Option[String] = findHeaderValue(Headers.Ect)

  /**
   * Application layer round trip time (RTT) in milliseconds, which includes the server processing time. This is part of
   * the Network Information API.
   */
  lazy val rtt: Option[String] = findHeaderValue(Headers.Rtt)

  /**
   * A string on that indicates the user agent's preference for reduced data usage.
   */
  lazy val saveData: Option[String] = findHeaderValue(Headers.SaveData)

  /**
   * Request header that indicates the user's tracking preference (Do Not Track). Deprecated in favor of
   * Global Privacy Control (GPC), which is communicated to servers using the Sec-GPC header, and accessible to clients
   * via navigator.globalPrivacyControl.
   */
  lazy val dnt: Option[String] = findHeaderValue(Headers.Dnt)

  /**
   * Response header that indicates the tracking status that applied to the corresponding request. Used in conjunction
   * with DNT.
   */
  lazy val tk: Option[String] = findHeaderValue(Headers.Tk)

  /**
   * Indicates whether the user consents to a website or service selling or sharing their personal information with
   * third parties.
   */
  lazy val secGpc: Option[String] = findHeaderValue(Headers.SecGpc)

  /**
   * Provides a mechanism to allow web applications to isolate their origins.
   */
  lazy val originIsolation: Option[String] = findHeaderValue(Headers.OriginIsolation)

  /**
   * Defines a mechanism that enables developers to declare a network error reporting policy.
   */
  lazy val nel: Option[String] = findHeaderValue(Headers.Nel)

  /**
   * Response header used to mark topics of interest inferred from a calling site's URL as observed in the response to
   * a request generated by a feature that enables the Topics API.
   */
  lazy val observeBrowsingTopics: Option[String] = findHeaderValue(Headers.ObserveBrowsingTopics)

  /**
   * Request header that sends the selected topics for the current user along with the associated request, which are
   * used by an ad tech platform to choose a personalized ad to display.
   */
  lazy val secBrowsingTopics: Option[String] = findHeaderValue(Headers.SecBrowsingTopics)

  /**
   * A client can send the Accept-Signature header field to indicate intention to take advantage of any available
   * signatures and to indicate what kinds of signatures it supports.
   */
  lazy val acceptSignature: Option[String] = findHeaderValue(Headers.AcceptSignature)

  /**
   * Indicates that the request has been conveyed in TLS early data.
   */
  lazy val earlyData: Option[String] = findHeaderValue(Headers.EarlyData)

  /**
   * Response header used to indicate that the associated Document should be placed in an origin-keyed agent cluster.
   * This isolation allows user agents to allocate implementation-specific resources for agent clusters, such as
   * processes or threads, more efficiently.
   */
  lazy val originAgentCluster: Option[String] = findHeaderValue(Headers.OriginAgentCluster)

  /**
   * Response header sent by a federated identity provider (IdP) to set its login status, meaning whether any users
   * are logged into the IdP on the current browser or not. This is stored by the browser and used by the FedCM API.
   */
  lazy val setLogin: Option[String] = findHeaderValue(Headers.SetLogin)

  /**
   * The Signature header field conveys a list of signatures for an exchange, each one accompanied by information about
   * how to determine the authority of and refresh that signature.
   */
  lazy val signature: Option[String] = findHeaderValue(Headers.Signature)

  /**
   * The Signed-Headers header field identifies an ordered list of response header fields to include in a signature.
   */
  lazy val signedHeaders: Option[String] = findHeaderValue(Headers.SignedHeaders)

  /**
   * Provides a list of URLs pointing to text resources containing speculation rule JSON definitions. When the response
   * is an HTML document, these rules will be added to the document's speculation rule set.
   */
  lazy val speculationRules: Option[String] = findHeaderValue(Headers.SpeculationRules)

  /**
   * Set by a navigation target to opt-in to using various higher-risk loading modes. For example, cross-origin,
   * same-site prerendering requires a Supports-Loading-Mode value of credentialed-prerender.
   */
  lazy val supportsLoadingMode: Option[String] = findHeaderValue(Headers.SupportsLoadingMode)

  /**
   * Identifies the originating IP addresses of a client connecting to a web server through an HTTP proxy or a load
   * balancer.
   */
  lazy val xForwardedFor: Option[String] = findHeaderValue(Headers.XForwardedFor)

  /**
   * Identifies the original host requested that a client used to connect to your proxy or load balancer.
   */
  lazy val xForwardedHost: Option[String] = findHeaderValue(Headers.XForwardedHost)

  /**
   * Identifies the protocol (HTTP or HTTPS) that a client used to connect to your proxy or load balancer.
   */
  lazy val xForwardedProto: Option[String] = findHeaderValue(Headers.XForwardedProto)

  /**
   * Controls DNS prefetching, a feature by which browsers proactively perform domain name resolution on both links
   * that the user may choose to follow as well as URLs for items referenced by the document, including images, CSS,
   * JavaScript, and so forth.
   */
  lazy val xDnsPrefetchControl: Option[String] = findHeaderValue(Headers.XDnsPrefetchControl)

  /**
   * The X-Robots-Tag HTTP header is used to indicate how a web page is to be indexed within public search engine
   * results. The header is effectively equivalent to meta name="robots" content="…".
   */
  lazy val xRobotsTag: Option[String] = findHeaderValue(Headers.XRobotsTag)

  /**
   * Implementation-specific header that may have various effects anywhere along the request-response chain. Used for
   * backwards compatibility with HTTP/1.0 caches where the Cache-Control header is not yet present.
   */
  lazy val pragma: Option[String] = findHeaderValue(Headers.Pragma)

  /**
   * General warning information about possible problems.
   */
  lazy val warning: Option[String] = findHeaderValue(Headers.Warning)
