package mouse.types

/**
 * Headers wrapper to extract the raw header values and utilize the MDN defined header keys.
 *
 * Reference: [[https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers]]
 * @param headers Raw header map
 */
case class HeaderValues(headers: Map[String, String]):
  /**
   * Lower-cased version of header keys to allow for header fields to be case-insensitive.
   */
  private lazy val normalizedHeaders = headers.map((k, v) => (k.toLowerCase, v))

  /**
   * Defines the authentication method that should be used to access a resource.
   */
  lazy val wwwAuthenticate: Option[String] = normalizedHeaders.get("www-authenticate")

  /**
   * Contains the credentials to authenticate a user-agent with a server.
   */
  lazy val authorization: Option[String] = normalizedHeaders.get("authorization")

  /**
   * Defines the authentication method that should be used to access a resource behind a proxy server.
   */
  lazy val proxyAuthenticate: Option[String] = normalizedHeaders.get("proxy-authenticate")

  /**
   * Contains the credentials to authenticate a user agent with a proxy server.
   */
  lazy val proxyAuthorization: Option[String] = normalizedHeaders.get("proxy-authorization")

  /**
   * The time, in seconds, that the object has been in a proxy cache.
   */
  lazy val age: Option[String] = normalizedHeaders.get("age")

  /**
   * Directives for caching mechanisms in both requests and responses.
   */
  lazy val cacheControl: Option[String] = normalizedHeaders.get("cache-control")

  /**
   * Clears browsing data (e.g. cookies, storage, cache) associated with the requesting website.
   */
  lazy val clearSiteData: Option[String] = normalizedHeaders.get("clear-site-data")

  /**
   * The date/time after which the response is considered stale.
   */
  lazy val expires: Option[String] = normalizedHeaders.get("expires")

  /**
   * Specifies a set of rules that define how a URL's query parameters will affect cache matching. These rules dictate
   * whether the same URL with different URL parameters should be saved as separate browser cache entries.
   */
  lazy val noVarySearch: Option[String] = normalizedHeaders.get("no-vary-search")

  /**
   * The last modification date of the resource, used to compare several versions of the same resource. It is less
   * accurate than ETag, but easier to calculate in some environments. Conditional requests using If-Modified-Since and
   * If-Unmodified-Since use this value to change the behavior of the request.
   */
  lazy val lastModified: Option[String] = normalizedHeaders.get("last-modified")

  /**
   * A unique string identifying the version of the resource. Conditional requests using If-Match and If-None-Match use
   * this value to change the behavior of the request.
   */
  lazy val eTag: Option[String] = normalizedHeaders.get("etag")

  /**
   * Makes the request conditional, and applies the method only if the stored resource matches one of the given ETags.
   */
  lazy val ifMatch: Option[String] = normalizedHeaders.get("if-match")

  /**
   * Makes the request conditional, and applies the method only if the stored resource doesn't match any of the given
   * ETags. This is used to update caches (for safe requests), or to prevent uploading a new resource when one already
   * exists.
   */
  lazy val ifNoneMatch: Option[String] = normalizedHeaders.get("if-none-match")

  /**
   * Makes the request conditional, and expects the resource to be transmitted only if it has been modified after the
   * given date. This is used to transmit data only when the cache is out of date.
   */
  lazy val ifModifiedSince: Option[String] = normalizedHeaders.get("if-modified-since")

  /**
   * Makes the request conditional, and expects the resource to be transmitted only if it has not been modified after
   * the given date. This ensures the coherence of a new fragment of a specific range with previous ones, or to
   * implement an optimistic concurrency control system when modifying existing documents.
   */
  lazy val ifUnmodifiedSince: Option[String] = normalizedHeaders.get("if-unmodified-since")

  /**
   * Determines how to match request headers to decide whether a cached response can be used rather than requesting a
   * fresh one from the origin server.
   */
  lazy val vary: Option[String] = normalizedHeaders.get("vary")

  /**
   * Controls whether the network connection stays open after the current transaction finishes.
   */
  lazy val connection: Option[String] = normalizedHeaders.get("connection")

  /**
   * Controls how long a persistent connection should stay open.
   */
  lazy val keepAlive: Option[String] = normalizedHeaders.get("keep-alive")

  /**
   * Informs the server about the types of data that can be sent back.
   */
  lazy val accept: Option[String] = normalizedHeaders.get("accept")

  /**
   * Advertises a client's supported character encodings. It is deprecated because UTF-8 has become ubiquitous and use
   * of the header makes client fingerprinting easier.
   */
  lazy val acceptCharset: Option[String] = normalizedHeaders.get("accept-charset")

  /**
   * The encoding algorithm, usually a compression algorithm, that can be used on the resource sent back.
   */
  lazy val acceptEncoding: Option[String] = normalizedHeaders.get("accept-encoding")

  /**
   * Informs the server about the human language the server is expected to send back. This is a hint and is not
   * necessarily under the full control of the user: the server should always pay attention not to override an explicit
   * user choice (like selecting a language from a dropdown).
   */
  lazy val acceptLanguage: Option[String] = normalizedHeaders.get("accept-language")

  /**
   * A request content negotiation response header that advertises which media type the server is able to understand in
   * a PATCH request.
   */
  lazy val acceptPatch: Option[String] = normalizedHeaders.get("accept-patch")

  /**
   * A request content negotiation response header that advertises which media type the server is able to understand in
   * a POST request.
   */
  lazy val acceptPost: Option[String] = normalizedHeaders.get("accept-post")

  /**
   * Indicates expectations that need to be fulfilled by the server to properly handle the request.
   */
  lazy val expect: Option[String] = normalizedHeaders.get("expect")

  /**
   * When using TRACE, indicates the maximum number of hops the request can do before being reflected to the sender.
   */
  lazy val maxForwards: Option[String] = normalizedHeaders.get("max-forwards")

  /**
   * Contains stored HTTP cookies previously sent by the server with the Set-Cookie header.
   */
  lazy val cookie: Option[String] = normalizedHeaders.get("cookie")

  /**
   * Send cookies from the server to the user-agent.
   */
  lazy val setCookie: Option[String] = normalizedHeaders.get("set-cookie")

  /**
   * Indicates whether the response to the request can be exposed when the credentials flag is true.
   */
  lazy val accessControlAllowCredentials: Option[String] = normalizedHeaders.get("access-control-allow-credentials")

  /**
   * Used in response to a preflight request to indicate which HTTP headers can be used when making the actual request.
   */
  lazy val accessControlAllowHeaders: Option[String] = normalizedHeaders.get("access-control-allow-headers")

  /**
   * Specifies the methods allowed when accessing the resource in response to a preflight request.
   */
  lazy val accessControlAllowMethods: Option[String] = normalizedHeaders.get("access-control-allow-methods")

  /**
   * Indicates whether the response can be shared.
   */
  lazy val accessControlAllowOrigin: Option[String] = normalizedHeaders.get("access-control-allow-origin")

  /**
   * Indicates which headers can be exposed as part of the response by listing their names.
   */
  lazy val accessControlExposeHeaders: Option[String] = normalizedHeaders.get("access-control-expose-headers")

  /**
   * Indicates how long the results of a preflight request can be cached.
   */
  lazy val accessControlMaxAge: Option[String] = normalizedHeaders.get("access-control-max-age")

  /**
   * Used when issuing a preflight request to let the server know which HTTP headers will be used when the actual
   * request is made.
   */
  lazy val accessControlRequestHeaders: Option[String] = normalizedHeaders.get("access-control-request-headers")

  /**
   * Used when issuing a preflight request to let the server know which HTTP method will be used when the actual
   * request is made.
   */
  lazy val accessControlRequestMethod: Option[String] = normalizedHeaders.get("access-control-request-method")

  /**
   * Indicates where a fetch originates from.
   */
  lazy val origin: Option[String] = normalizedHeaders.get("origin")

  /**
   * Specifies origins that are allowed to see values of attributes retrieved via features of the Resource Timing API,
   * which would otherwise be reported as zero due to cross-origin restrictions.
   */
  lazy val timingAllowOrigin: Option[String] = normalizedHeaders.get("timing-allow-origin")

  /**
   * Indicates if the resource transmitted should be displayed inline (default behavior without the header), or if it
   * should be handled like a download and the browser should present a "Save As" dialog.
   */
  lazy val contentDisposition: Option[String] = normalizedHeaders.get("content-disposition")

  /**
   * Provides a digest of the stream of octets framed in an HTTP message (the message content) dependent on
   * Content-Encoding and Content-Range.
   */
  lazy val contentDigest: Option[String] = normalizedHeaders.get("content-digest")

  /**
   * Provides a digest of the a resource. See Content-Digest and Repr-Digest.
   */
  lazy val digest: Option[String] = normalizedHeaders.get("digest")

  /**
   * Provides a digest of the selected representation of the target resource before transmission. Unlike the
   * Content-Digest, the digest does not consider Content-Encoding or Content-Range.
   */
  lazy val reprDigest: Option[String] = normalizedHeaders.get("repr-digest")

  /**
   * States the wish for a Content-Digest header. It is the Content- analogue of Want-Repr-Digest.
   */
  lazy val wantContentDigest: Option[String] = normalizedHeaders.get("want-content-digest")

  /**
   * States the wish for a Digest header. See Want-Content-Digest and Want-Repr-Digest instead.
   */
  lazy val wantDigest: Option[String] = normalizedHeaders.get("want-digest")

  /**
   * States the wish for a Repr-Digest header. It is the Repr- analogue of Want-Content-Digest.
   */
  lazy val wantReprDigest: Option[String] = normalizedHeaders.get("want-repr-digest")

  /**
   * The size of the resource, in decimal number of bytes.
   */
  lazy val contentLength: Option[String] = normalizedHeaders.get("content-length")

  /**
   * Indicates the media type of the resource.
   */
  lazy val contentType: Option[String] = normalizedHeaders.get("content-type")

  /**
   * Used to specify the compression algorithm.
   */
  lazy val contentEncoding: Option[String] = normalizedHeaders.get("content-encoding")

  /**
   * Describes the human language(s) intended for the audience, so that it allows a user to differentiate according to
   * the users' own preferred language.
   */
  lazy val contentLanguage: Option[String] = normalizedHeaders.get("content-language")

  /**
   * Indicates an alternate location for the returned data.
   */
  lazy val contentLocation: Option[String] = normalizedHeaders.get("content-location")

  /**
   * Contains information from the client-facing side of proxy servers that is altered or lost when a proxy is involved
   * in the path of the request.
   */
  lazy val forwarded: Option[String] = normalizedHeaders.get("forwarded")

  /**
   * Added by proxies, both forward and reverse proxies, and can appear in the request headers and the response headers.
   */
  lazy val via: Option[String] = normalizedHeaders.get("via")

  /**
   * Indicates if the server supports range requests, and if so in which unit the range can be expressed.
   */
  lazy val acceptRanges: Option[String] = normalizedHeaders.get("accept-ranges")

  /**
   * Indicates the part of a document that the server should return.
   */
  lazy val range: Option[String] = normalizedHeaders.get("range")

  /**
   * Creates a conditional range request that is only fulfilled if the given etag or date matches the remote resource.
   * Used to prevent downloading two ranges from incompatible version of the resource.
   */
  lazy val ifRange: Option[String] = normalizedHeaders.get("if-range")

  /**
   * Indicates where in a full body message a partial message belongs.
   */
  lazy val contentRange: Option[String] = normalizedHeaders.get("content-range")

  /**
   * Indicates the URL to redirect a page to.
   */
  lazy val location: Option[String] = normalizedHeaders.get("location")

  /**
   * Directs the browser to reload the page or redirect to another. Takes the same value as the meta element with
   * http-equiv="refresh".
   */
  lazy val refresh: Option[String] = normalizedHeaders.get("refresh")

  /**
   * Contains an Internet email address for a human user who controls the requesting user agent.
   */
  lazy val from: Option[String] = normalizedHeaders.get("from")

  /**
   * Specifies the domain name of the server (for virtual hosting), and (optionally) the TCP port number on which
   * the server is listening.
   */
  lazy val host: Option[String] = normalizedHeaders.get("host")

  /**
   * The address of the previous web page from which a link to the currently requested page was followed.
   */
  lazy val referer: Option[String] = normalizedHeaders.get("referer")

  /**
   * Governs which referrer information sent in the Referer header should be included with requests made.
   */
  lazy val referrerPolicy: Option[String] = normalizedHeaders.get("referrer-policy")

  /**
   * Contains a characteristic string that allows the network protocol peers to identify the application type, operating
   * system, software vendor or software version of the requesting software user agent.
   */
  lazy val userAgent: Option[String] = normalizedHeaders.get("user-agent")

  /**
   * Lists the set of HTTP request methods supported by a resource.
   */
  lazy val allow: Option[String] = normalizedHeaders.get("allow")

  /**
   * Contains information about the software used by the origin server to handle the request.
   */
  lazy val server: Option[String] = normalizedHeaders.get("server")

  /**
   * Allows a server to declare an embedder policy for a given document.
   */
  lazy val crossOriginEmbedderPolicy: Option[String] = normalizedHeaders.get("cross-origin-embedder-policy")

  /**
   * Prevents other domains from opening/controlling a window.
   */
  lazy val crossOriginOpenerPolicy: Option[String] = normalizedHeaders.get("cross-origin-opener-policy")

  /**
   * Prevents other domains from reading the response of the resources to which this header is applied. See also CORP
   * explainer article.
   */
  lazy val crossOriginResourcePolicy: Option[String] = normalizedHeaders.get("cross-origin-resource-policy")

  /**
   * Controls resources the user agent is allowed to load for a given page.
   */
  lazy val contentSecurityPolicy: Option[String] = normalizedHeaders.get("content-security-policy")

  /**
   * Allows web developers to experiment with policies by monitoring, but not enforcing, their effects. These violation
   * reports consist of JSON documents sent via an HTTP POST request to the specified URI.
   */
  lazy val contentSecurityPolicyReportOnly: Option[String] = normalizedHeaders.get(
    "content-security-policy-report-only")

  /**
   * Lets sites opt in to reporting and enforcement of Certificate Transparency to detect use of misissued certificates
   * for that site.
   */
  lazy val expectCt: Option[String] = normalizedHeaders.get("expect-ct")

  /**
   * Provides a mechanism to allow and deny the use of browser features in a website's own frame, and in iframes that it
   * embeds.
   */
  lazy val permissionsPolicy: Option[String] = normalizedHeaders.get("permissions-policy")

  /**
   * Force communication using HTTPS instead of HTTP.
   */
  lazy val strictTransportSecurity: Option[String] = normalizedHeaders.get("strict-transport-security")

  /**
   * Sends a signal to the server expressing the client's preference for an encrypted and authenticated response, and
   * that it can successfully handle the upgrade-insecure-requests directive.
   */
  lazy val upgradeInsecureRequests: Option[String] = normalizedHeaders.get("upgrade-insecure-requests")

  /**
   * Disables MIME sniffing and forces browser to use the type given in Content-Type.
   */
  lazy val xContentTypeOptions: Option[String] = normalizedHeaders.get("x-content-type-options")

  /**
   * Indicates whether a browser should be allowed to render a page in a frame, iframe, embed or object.
   */
  lazy val xFrameOptions: Option[String] = normalizedHeaders.get("x-frame-options")

  /**
   * Specifies if a cross-domain policy file (crossdomain.xml) is allowed. The file may define a policy to grant
   * clients, such as Adobe's Flash Player (now obsolete), Adobe Acrobat, Microsoft Silverlight (now obsolete),
   * or Apache Flex, permission to handle data across domains that would otherwise be restricted due to the Same-Origin
   * Policy. See the Cross-domain Policy File Specification for more information.
   */
  lazy val xPermittedCrossDomainPolicies: Option[String] = normalizedHeaders.get("x-permitted-cross-domain-policies")

  /**
   * May be set by hosting environments or other frameworks and contains information about them while not providing any
   * usefulness to the application or its visitors. Unset this header to avoid exposing potential vulnerabilities.
   */
  lazy val xPoweredBy: Option[String] = normalizedHeaders.get("x-powered-by")

  /**
   * Enables cross-site scripting filtering.
   */
  lazy val xXssProtection: Option[String] = normalizedHeaders.get("x-xss-protection")

  /**
   * Indicates the relationship between a request initiator's origin and its target's origin. It is a Structured Header
   * whose value is a token with possible values cross-site, same-origin, same-site, and none.
   */
  lazy val secFetchSite: Option[String] = normalizedHeaders.get("sec-fetch-site")

  /**
   * Indicates the request's mode to a server. It is a Structured Header whose value is a token with possible values
   * cors, navigate, no-cors, same-origin, and websocket.
   */
  lazy val secFetchMode: Option[String] = normalizedHeaders.get("sec-fetch-mode")

  /**
   * Indicates whether or not a navigation request was triggered by user activation. It is a Structured Header whose
   * value is a boolean so possible values are ?0 for false and ?1 for true.
   */
  lazy val secFetchUser: Option[String] = normalizedHeaders.get("sec-fetch-user")

  /**
   * Indicates the request's destination. It is a Structured Header whose value is a token with possible values audio,
   * audioworklet, document, embed, empty, font, image, manifest, object, paintworklet, report, script, serviceworker,
   * sharedworker, style, track, video, worker, and xslt.
   */
  lazy val secFetchDest: Option[String] = normalizedHeaders.get("sec-fetch-dest")

  /**
   * Indicates the purpose of the request, when the purpose is something other than immediate use by the user-agent.
   * The header currently has one possible value, prefetch, which indicates that the resource is being fetched
   * preemptively for a possible future navigation.
   */
  lazy val secPurpose: Option[String] = normalizedHeaders.get("sec-purpose")

  /**
   * A request header sent in preemptive request to fetch() a resource during service worker boot. The value, which is
   * set with NavigationPreloadManager.setHeaderValue(), can be used to inform a server that a different resource
   * should be returned than in a normal fetch() operation.
   */
  lazy val serviceWorkerNavigationPreload: Option[String] = normalizedHeaders.get("service-worker-navigation-preload")

  /**
   * Response header used to specify server endpoints where the browser should send warning and error reports when
   * using the Reporting API.
   */
  lazy val reportingEndpoints: Option[String] = normalizedHeaders.get("reporting-endpoints")

  /**
   * Response header used to specify server endpoints where the browser should send warning and error reports when
   * using the Reporting API.
   */
  lazy val reportTo: Option[String] = normalizedHeaders.get("report-to")

  /**
   * Specifies the form of encoding used to safely transfer the resource to the user.
   */
  lazy val transferEncoding: Option[String] = normalizedHeaders.get("transfer-encoding")

  /**
   * Specifies the transfer encodings the user agent is willing to accept.
   */
  lazy val tE: Option[String] = normalizedHeaders.get("te")

  /**
   * Allows the sender to include additional fields at the end of chunked message.
   */
  lazy val trailer: Option[String] = normalizedHeaders.get("trailer")

  /**
   * Response header that indicates that the server is willing to upgrade to a WebSocket connection.
   */
  lazy val secWebSocketAccept: Option[String] = normalizedHeaders.get("sec-websocket-accept")

  /**
   * In requests, this header indicates the WebSocket extensions supported by the client in preferred order. In
   * responses, it indicates the extension selected by the server from the client's preferences.
   */
  lazy val secWebSocketExtensions: Option[String] = normalizedHeaders.get("sec-websocket-extensions")

  /**
   * Request header containing a key that verifies that the client explicitly intends to open a WebSocket.
   */
  lazy val secWebSocketKey: Option[String] = normalizedHeaders.get("sec-websocket-key")

  /**
   * In requests, this header indicates the sub-protocols supported by the client in preferred order. In responses, it
   * indicates the the sub-protocol selected by the server from the client's preferences.
   */
  lazy val secWebSocketProtocol: Option[String] = normalizedHeaders.get("sec-websocket-protocol")

  /**
   * In requests, this header indicates the version of the WebSocket protocol used by the client. In responses, it is
   * sent only if the requested protocol version is not supported by the server, and lists the versions that the server
   * supports.
   */
  lazy val secWebSocketVersion: Option[String] = normalizedHeaders.get("sec-websocket-version")

  /**
   * Used to list alternate ways to reach this service.
   */
  lazy val altSvc: Option[String] = normalizedHeaders.get("alt-svc")

  /**
   * Used to identify the alternative service in use.
   */
  lazy val altUsed: Option[String] = normalizedHeaders.get("alt-used")

  /**
   * Contains the date and time at which the message was originated.
   */
  lazy val date: Option[String] = normalizedHeaders.get("date")

  /**
   * This entity-header field provides a means for serializing one or more links in HTTP headers. It is semantically
   * equivalent to the HTML link element.
   */
  lazy val link: Option[String] = normalizedHeaders.get("link")

  /**
   * Indicates how long the user agent should wait before making a follow-up request.
   */
  lazy val retryAfter: Option[String] = normalizedHeaders.get("retry-after")

  /**
   * Communicates one or more metrics and descriptions for the given request-response cycle.
   */
  lazy val serverTiming: Option[String] = normalizedHeaders.get("server-timing")

  /**
   * Used to remove the path restriction by including this header in the response of the Service Worker script.
   */
  lazy val serviceWorkerAllowed: Option[String] = normalizedHeaders.get("service-worker-allowed")

  /**
   * Links generated code to a source map.
   */
  lazy val sourceMap: Option[String] = normalizedHeaders.get("sourcemap")

  /**
   * This HTTP/1.1 (only) header can be used to upgrade an already established client/server connection to a different
   * protocol (over the same transport protocol). For example, it can be used by a client to upgrade a connection from
   * HTTP 1.1 to HTTP 2.0, or an HTTP or HTTPS connection into a WebSocket.
   */
  lazy val upgrade: Option[String] = normalizedHeaders.get("upgrade")

  /**
   * Provides a hint from about the priority of a particular resource request on a particular connection. The value can
   * be sent in a request to indicate the client priority, or in a response if the server chooses to reprioritize the
   * request.
   */
  lazy val priority: Option[String] = normalizedHeaders.get("priority")

  /**
   * Used to indicate that the response corresponding to the current request is eligible to take part in attribution
   * reporting, by registering either an attribution source or trigger.
   */
  lazy val attributionReportingEligible: Option[String] = normalizedHeaders.get("attribution-reporting-eligible")

  /**
   * Included as part of a response to a request that included an Attribution-Reporting-Eligible header, this is used
   * to register an attribution source.
   */
  lazy val attributionReportingRegisterSource: Option[String] = normalizedHeaders.get(
    "attribution-reporting-register-source")

  /**
   * Included as part of a response to a request that included an Attribution-Reporting-Eligible header, this is used
   * to register an attribution trigger.
   */
  lazy val attributionReportingRegisterTrigger: Option[String] = normalizedHeaders.get(
    "attribution-reporting-register-trigger")

  /**
   * Servers can advertise support for Client Hints using the Accept-CH header field or an equivalent HTML meta element
   * with http-equiv attribute.
   */
  lazy val acceptCh: Option[String] = normalizedHeaders.get("accept-ch")

  /**
   * Servers use Critical-CH along with Accept-CH to specify that accepted client hints are also critical client hints.
   */
  lazy val criticalCh: Option[String] = normalizedHeaders.get("critical-ch")

  /**
   * User agent's branding and version.
   */
  lazy val secChUa: Option[String] = normalizedHeaders.get("sec-ch-ua")

  /**
   * User agent's underlying platform architecture.
   */
  lazy val secChUaArch: Option[String] = normalizedHeaders.get("sec-ch-ua-arch")

  /**
   * User agent's underlying CPU architecture bitness (for example "64" bit).
   */
  lazy val secChUaBitness: Option[String] = normalizedHeaders.get("sec-ch-ua-bitness")

  /**
   * User agent's form-factors, describing how the user interacts with the user-agent.
   */
  lazy val secChUaFormFactor: Option[String] = normalizedHeaders.get("sec-ch-ua-form-factor")

  /**
   * User agent's full version string.
   */
  lazy val secChUaFullVersion: Option[String] = normalizedHeaders.get("sec-ch-ua-full-version")

  /**
   * Full version for each brand in the user agent's brand list.
   */
  lazy val secChUaFullVersionList: Option[String] = normalizedHeaders.get("sec-ch-ua-full-version-list")

  /**
   * User agent is running on a mobile device or, more generally, prefers a "mobile" user experience.
   */
  lazy val secChUaMobile: Option[String] = normalizedHeaders.get("sec-ch-ua-mobile")

  /**
   * User agent's device model.
   */
  lazy val secChUaModel: Option[String] = normalizedHeaders.get("sec-ch-ua-model")

  /**
   * User agent's underlying operation system/platform.
   */
  lazy val secChUaPlatform: Option[String] = normalizedHeaders.get("sec-ch-ua-platform")

  /**
   * User agent's underlying operation system version.
   */
  lazy val secChUaPlatformVersion: Option[String] = normalizedHeaders.get("sec-ch-ua-platform-version")

  /**
   * Whether or not the user agent binary is running in 32-bit mode on 64-bit Windows.
   */
  lazy val secChUaWoW64: Option[String] = normalizedHeaders.get("sec-ch-ua-wow64")

  /**
   * User's preference of dark or light color scheme.
   */
  lazy val secChPrefersColorScheme: Option[String] = normalizedHeaders.get("sec-ch-prefers-color-scheme")

  /**
   * User's preference to see fewer animations and content layout shifts.
   */
  lazy val secChPrefersReducedMotion: Option[String] = normalizedHeaders.get("sec-ch-prefers-reduced-motion")

  /**
   * Request header indicates the user agent's preference for reduced transparency.
   */
  lazy val secChPrefersReducedTransparency: Option[String] = normalizedHeaders.get(
    "sec-ch-prefers-reduced-transparency")

  /**
   * Response header used to confirm the image device to pixel ratio (DPR) in requests where the screen DPR client hint
   * was used to select an image resource.
   */
  lazy val contentDPR: Option[String] = normalizedHeaders.get("content-dpr")

  /**
   * Approximate amount of available client RAM memory. This is part of the Device Memory API.
   */
  lazy val deviceMemory: Option[String] = normalizedHeaders.get("device-memory")

  /**
   * Request header that provides the client device pixel ratio (the number of physical device pixels for each CSS pixel).
   */
  lazy val dPR: Option[String] = normalizedHeaders.get("dpr")

  /**
   * Request header provides the client's layout viewport width in CSS pixels.
   */
  lazy val viewportWidth: Option[String] = normalizedHeaders.get("viewport-width")

  /**
   * Request header indicates the desired resource width in physical pixels (the intrinsic size of an image).
   */
  lazy val width: Option[String] = normalizedHeaders.get("width")

  /**
   * Approximate bandwidth of the client's connection to the server, in Mbps. This is part of the Network Information API.
   */
  lazy val downlink: Option[String] = normalizedHeaders.get("downlink")

  /**
   * The effective connection type ("network profile") that best matches the connection's latency and bandwidth. This
   * is part of the Network Information API.
   */
  lazy val ect: Option[String] = normalizedHeaders.get("ect")

  /**
   * Application layer round trip time (RTT) in milliseconds, which includes the server processing time. This is part of
   * the Network Information API.
   */
  lazy val rtt: Option[String] = normalizedHeaders.get("rtt")

  /**
   * A string on that indicates the user agent's preference for reduced data usage.
   */
  lazy val saveData: Option[String] = normalizedHeaders.get("save-data")

  /**
   * Request header that indicates the user's tracking preference (Do Not Track). Deprecated in favor of
   * Global Privacy Control (GPC), which is communicated to servers using the Sec-GPC header, and accessible to clients
   * via navigator.globalPrivacyControl.
   */
  lazy val dnt: Option[String] = normalizedHeaders.get("dnt")

  /**
   * Response header that indicates the tracking status that applied to the corresponding request. Used in conjunction
   * with DNT.
   */
  lazy val tk: Option[String] = normalizedHeaders.get("tk")

  /**
   * Indicates whether the user consents to a website or service selling or sharing their personal information with
   * third parties.
   */
  lazy val secGPC: Option[String] = normalizedHeaders.get("sec-gpc")

  /**
   * Provides a mechanism to allow web applications to isolate their origins.
   */
  lazy val originIsolation: Option[String] = normalizedHeaders.get("origin-isolation")

  /**
   * Defines a mechanism that enables developers to declare a network error reporting policy.
   */
  lazy val nel: Option[String] = normalizedHeaders.get("nel")

  /**
   * Response header used to mark topics of interest inferred from a calling site's URL as observed in the response to
   * a request generated by a feature that enables the Topics API.
   */
  lazy val observeBrowsingTopics: Option[String] = normalizedHeaders.get("observe-browsing-topics")

  /**
   * Request header that sends the selected topics for the current user along with the associated request, which are
   * used by an ad tech platform to choose a personalized ad to display.
   */
  lazy val secBrowsingTopics: Option[String] = normalizedHeaders.get("sec-browsing-topics")

  /**
   * A client can send the Accept-Signature header field to indicate intention to take advantage of any available
   * signatures and to indicate what kinds of signatures it supports.
   */
  lazy val acceptSignature: Option[String] = normalizedHeaders.get("accept-signature")

  /**
   * Indicates that the request has been conveyed in TLS early data.
   */
  lazy val earlyData: Option[String] = normalizedHeaders.get("early-data")

  /**
   * Response header used to indicate that the associated Document should be placed in an origin-keyed agent cluster.
   * This isolation allows user agents to allocate implementation-specific resources for agent clusters, such as
   * processes or threads, more efficiently.
   */
  lazy val originAgentCluster: Option[String] = normalizedHeaders.get("origin-agent-cluster")

  /**
   * Response header sent by a federated identity provider (IdP) to set its login status, meaning whether any users
   * are logged into the IdP on the current browser or not. This is stored by the browser and used by the FedCM API.
   */
  lazy val setLogin: Option[String] = normalizedHeaders.get("set-login")

  /**
   * The Signature header field conveys a list of signatures for an exchange, each one accompanied by information about
   * how to determine the authority of and refresh that signature.
   */
  lazy val signature: Option[String] = normalizedHeaders.get("signature")

  /**
   * The Signed-Headers header field identifies an ordered list of response header fields to include in a signature.
   */
  lazy val signedHeaders: Option[String] = normalizedHeaders.get("signed-headers")

  /**
   * Provides a list of URLs pointing to text resources containing speculation rule JSON definitions. When the response
   * is an HTML document, these rules will be added to the document's speculation rule set.
   */
  lazy val speculationRules: Option[String] = normalizedHeaders.get("speculation-rules")

  /**
   * Set by a navigation target to opt-in to using various higher-risk loading modes. For example, cross-origin,
   * same-site prerendering requires a Supports-Loading-Mode value of credentialed-prerender.
   */
  lazy val supportsLoadingMode: Option[String] = normalizedHeaders.get("supports-loading-mode")

  /**
   * Identifies the originating IP addresses of a client connecting to a web server through an HTTP proxy or a load
   * balancer.
   */
  lazy val xForwardedFor: Option[String] = normalizedHeaders.get("x-forwarded-for")

  /**
   * Identifies the original host requested that a client used to connect to your proxy or load balancer.
   */
  lazy val xForwardedHost: Option[String] = normalizedHeaders.get("x-forwarded-host")

  /**
   * Identifies the protocol (HTTP or HTTPS) that a client used to connect to your proxy or load balancer.
   */
  lazy val xForwardedProto: Option[String] = normalizedHeaders.get("x-forwarded-proto")

  /**
   * Controls DNS prefetching, a feature by which browsers proactively perform domain name resolution on both links
   * that the user may choose to follow as well as URLs for items referenced by the document, including images, CSS,
   * JavaScript, and so forth.
   */
  lazy val xDnsPrefetchControl: Option[String] = normalizedHeaders.get("x-dns-prefetch-control")

  /**
   * The X-Robots-Tag HTTP header is used to indicate how a web page is to be indexed within public search engine
   * results. The header is effectively equivalent to meta name="robots" content="…".
   */
  lazy val xRobotsTag: Option[String] = normalizedHeaders.get("x-robots-tag")

  /**
   * Implementation-specific header that may have various effects anywhere along the request-response chain. Used for
   * backwards compatibility with HTTP/1.0 caches where the Cache-Control header is not yet present.
   */
  lazy val pragma: Option[String] = normalizedHeaders.get("pragma")

  /**
   * General warning information about possible problems.
   */
  lazy val warning: Option[String] = normalizedHeaders.get("warning")
