package mouse.types

/**
 * Collection of header keys which are commonly known and well-defined.
 *
 * Reference: [[https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers]]
 */
object Headers:

  /**
   * Defines the authentication method that should be used to access a resource.
   */
  lazy val WwwAuthenticate: String = "WWW-Authenticate"

  /**
   * Contains the credentials to authenticate a user-agent with a server.
   */
  lazy val Authorization: String = "Authorization"

  /**
   * Defines the authentication method that should be used to access a resource behind a proxy server.
   */
  lazy val ProxyAuthenticate: String = "Proxy-Authenticate"

  /**
   * Contains the credentials to authenticate a user agent with a proxy server.
   */
  lazy val ProxyAuthorization: String = "Proxy-Authorization"

  /**
   * The time, in seconds, that the object has been in a proxy cache.
   */
  lazy val Age: String = "Age"

  /**
   * Directives for caching mechanisms in both requests and responses.
   */
  lazy val CacheControl: String = "Cache-Control"

  /**
   * Clears browsing data (e.g. cookies, storage, cache) associated with the requesting website.
   */
  lazy val ClearSiteData: String = "Clear-Site-Data"

  /**
   * The date/time after which the response is considered stale.
   */
  lazy val Expires: String = "Expires"

  /**
   * Specifies a set of rules that define how a URL's query parameters will affect cache matching. These rules dictate
   * whether the same URL with different URL parameters should be saved as separate browser cache entries.
   */
  lazy val NoVarySearch: String = "No-Vary-Search"

  /**
   * The last modification date of the resource, used to compare several versions of the same resource. It is less
   * accurate than ETag, but easier to calculate in some environments. Conditional requests using If-Modified-Since and
   * If-Unmodified-Since use this value to change the behavior of the request.
   */
  lazy val LastModified: String = "Last-Modified"

  /**
   * A unique string identifying the version of the resource. Conditional requests using If-Match and If-None-Match use
   * this value to change the behavior of the request.
   */
  lazy val ETag: String = "ETag"

  /**
   * Makes the request conditional, and applies the method only if the stored resource matches one of the given ETags.
   */
  lazy val IfMatch: String = "If-Match"

  /**
   * Makes the request conditional, and applies the method only if the stored resource doesn't match any of the given
   * ETags. This is used to update caches (for safe requests), or to prevent uploading a new resource when one already
   * exists.
   */
  lazy val IfNoneMatch: String = "If-None-Match"

  /**
   * Makes the request conditional, and expects the resource to be transmitted only if it has been modified after the
   * given date. This is used to transmit data only when the cache is out of date.
   */
  lazy val IfModifiedSince: String = "If-Modified-Since"

  /**
   * Makes the request conditional, and expects the resource to be transmitted only if it has not been modified after
   * the given date. This ensures the coherence of a new fragment of a specific range with previous ones, or to
   * implement an optimistic concurrency control system when modifying existing documents.
   */
  lazy val IfUnmodifiedSince: String = "If-Unmodified-Since"

  /**
   * Determines how to match request headers to decide whether a cached response can be used rather than requesting a
   * fresh one from the origin server.
   */
  lazy val Vary: String = "Vary"

  /**
   * Controls whether the network connection stays open after the current transaction finishes.
   */
  lazy val Connection: String = "Connection"

  /**
   * Controls how long a persistent connection should stay open.
   */
  lazy val KeepAlive: String = "Keep-Alive"

  /**
   * Informs the server about the types of data that can be sent back.
   */
  lazy val Accept: String = "Accept"

  /**
   * Advertises a client's supported character encodings. It is deprecated because UTF-8 has become ubiquitous and use
   * of the header makes client fingerprinting easier.
   */
  lazy val AcceptCharset: String = "Accept-Charset"

  /**
   * The encoding algorithm, usually a compression algorithm, that can be used on the resource sent back.
   */
  lazy val AcceptEncoding: String = "Accept-Encoding"

  /**
   * Informs the server about the human language the server is expected to send back. This is a hint and is not
   * necessarily under the full control of the user: the server should always pay attention not to override an explicit
   * user choice (like selecting a language from a dropdown).
   */
  lazy val AcceptLanguage: String = "Accept-Language"

  /**
   * A request content negotiation response header that advertises which media type the server is able to understand in
   * a PATCH request.
   */
  lazy val AcceptPatch: String = "Accept-Patch"

  /**
   * A request content negotiation response header that advertises which media type the server is able to understand in
   * a POST request.
   */
  lazy val AcceptPost: String = "Accept-Post"

  /**
   * Indicates expectations that need to be fulfilled by the server to properly handle the request.
   */
  lazy val Expect: String = "Expect"

  /**
   * When using TRACE, indicates the maximum number of hops the request can do before being reflected to the sender.
   */
  lazy val MaxForwards: String = "Max-Forwards"

  /**
   * Contains stored HTTP cookies previously sent by the server with the Set-Cookie header.
   */
  lazy val Cookie: String = "Cookie"

  /**
   * Send cookies from the server to the user-agent.
   */
  lazy val SetCookie: String = "Set-Cookie"

  /**
   * Indicates whether the response to the request can be exposed when the credentials flag is true.
   */
  lazy val AccessControlAllowCredentials: String = "Access-Control-Allow-Credentials"

  /**
   * Used in response to a preflight request to indicate which HTTP headers can be used when making the actual request.
   */
  lazy val AccessControlAllowHeaders: String = "Access-Control-Allow-Headers"

  /**
   * Specifies the methods allowed when accessing the resource in response to a preflight request.
   */
  lazy val AccessControlAllowMethods: String = "Access-Control-Allow-Methods"

  /**
   * Indicates whether the response can be shared.
   */
  lazy val AccessControlAllowOrigin: String = "Access-Control-Allow-Origin"

  /**
   * Indicates which headers can be exposed as part of the response by listing their names.
   */
  lazy val AccessControlExposeHeaders: String = "Access-Control-Expose-Headers"

  /**
   * Indicates how long the results of a preflight request can be cached.
   */
  lazy val AccessControlMaxAge: String = "Access-Control-Max-Age"

  /**
   * Used when issuing a preflight request to let the server know which HTTP headers will be used when the actual
   * request is made.
   */
  lazy val AccessControlRequestHeaders: String = "Access-Control-Request-Headers"

  /**
   * Used when issuing a preflight request to let the server know which HTTP method will be used when the actual
   * request is made.
   */
  lazy val AccessControlRequestMethod: String = "Access-Control-Request-Method"

  /**
   * Indicates where a fetch originates from.
   */
  lazy val Origin: String = "Origin"

  /**
   * Specifies origins that are allowed to see values of attributes retrieved via features of the Resource Timing API,
   * which would otherwise be reported as zero due to cross-Origin restrictions.
   */
  lazy val TimingAllowOrigin: String = "Timing-Allow-Origin"

  /**
   * Indicates if the resource transmitted should be displayed inline (default behavior without the header), or if it
   * should be handled like a download and the browser should present a "Save As" dialog.
   */
  lazy val ContentDisposition: String = "Content-Disposition"

  /**
   * Provides a digest of the stream of octets framed in an HTTP message (the message content) dependent on
   * Content-Encoding and Content-Range.
   */
  lazy val ContentDigest: String = "Content-Digest"

  /**
   * Provides a digest of the a resource. See Content-Digest and Repr-Digest.
   */
  lazy val Digest: String = "Digest"

  /**
   * Provides a digest of the selected representation of the target resource before transmission. Unlike the
   * Content-Digest, the digest does not consider Content-Encoding or Content-Range.
   */
  lazy val ReprDigest: String = "Repr-Digest"

  /**
   * States the wish for a Content-Digest header. It is the Content- analogue of Want-Repr-Digest.
   */
  lazy val WantContentDigest: String = "Want-Content-Digest"

  /**
   * States the wish for a Digest header. See Want-Content-Digest and Want-Repr-Digest instead.
   */
  lazy val WantDigest: String = "Want-Digest"

  /**
   * States the wish for a Repr-Digest header. It is the Repr- analogue of Want-Content-Digest.
   */
  lazy val WantReprDigest: String = "Want-Repr-Digest"

  /**
   * The size of the resource, in decimal number of bytes.
   */
  lazy val ContentLength: String = "Content-Length"

  /**
   * Indicates the media type of the resource.
   */
  lazy val ContentType: String = "Content-Type"

  /**
   * Used to specify the compression algorithm.
   */
  lazy val ContentEncoding: String = "Content-Encoding"

  /**
   * Describes the human language(s) intended for the audience, so that it allows a user to differentiate according to
   * the users' own preferred language.
   */
  lazy val ContentLanguage: String = "Content-Language"

  /**
   * Indicates an alternate location for the returned data.
   */
  lazy val ContentLocation: String = "Content-Location"

  /**
   * Contains information from the client-Facing side of proxy servers that is altered or lost when a proxy is involved
   * in the path of the request.
   */
  lazy val Forwarded: String = "Forwarded"

  /**
   * Added by proxies, both forward and reverse proxies, and can appear in the request headers and the response headers.
   */
  lazy val Via: String = "Via"

  /**
   * Indicates if the server supports range requests, and if so in which unit the range can be expressed.
   */
  lazy val AcceptRanges: String = "Accept-Ranges"

  /**
   * Indicates the part of a document that the server should return.
   */
  lazy val Range: String = "Range"

  /**
   * Creates a conditional range request that is only fulfilled if the given etag or date matches the remote resource.
   * Used to prevent downloading two ranges from incompatible version of the resource.
   */
  lazy val IfRange: String = "If-Range"

  /**
   * Indicates where in a full body message a partial message belongs.
   */
  lazy val ContentRange: String = "Content-Range"

  /**
   * Indicates the URL to redirect a page to.
   */
  lazy val Location: String = "Location"

  /**
   * Directs the browser to reload the page or redirect to another. Takes the same value as the meta element with
   * http-Equiv="refresh".
   */
  lazy val Refresh: String = "Refresh"

  /**
   * Contains an Internet email address for a human user who controls the requesting user agent.
   */
  lazy val From: String = "From"

  /**
   * Specifies the domain name of the server (for virtual hosting), and (optionally) the TCP port number on which
   * the server is listening.
   */
  lazy val Host: String = "Host"

  /**
   * The address of the previous web page from which a link to the currently requested page was followed.
   */
  lazy val Referer: String = "Referer"

  /**
   * Governs which referrer information sent in the Referer header should be included with requests made.
   */
  lazy val ReferrerPolicy: String = "Referrer-Policy"

  /**
   * Contains a characteristic string that allows the network protocol peers to identify the application type, operating
   * system, software vendor or software version of the requesting software user agent.
   */
  lazy val UserAgent: String = "User-Agent"

  /**
   * Lists the set of HTTP request methods supported by a resource.
   */
  lazy val Allow: String = "Allow"

  /**
   * Contains information about the software used by the origin server to handle the request.
   */
  lazy val Server: String = "Server"

  /**
   * Allows a server to declare an embedder policy for a given document.
   */
  lazy val CrossOriginEmbedderPolicy: String = "Cross-Origin-Embedder-Policy"

  /**
   * Prevents other domains from opening/controlling a window.
   */
  lazy val CrossOriginOpenerPolicy: String = "Cross-Origin-Opener-Policy"

  /**
   * Prevents other domains from reading the response of the resources to which this header is applied. See also CORP
   * explainer article.
   */
  lazy val CrossOriginResourcePolicy: String = "Cross-Origin-Resource-Policy"

  /**
   * Controls resources the user agent is allowed to load for a given page.
   */
  lazy val ContentSecurityPolicy: String = "Content-Security-Policy"

  /**
   * Allows web developers to experiment with policies by monitoring, but not enforcing, their effects. These violation
   * reports consist of JSON documents sent via an HTTP POST request to the specified URI.
   */
  lazy val ContentSecurityPolicyReportOnly: String = "Content-Security-Policy-Report-Only"

  /**
   * Lets sites opt in to reporting and enforcement of Certificate Transparency to detect use of misissued certificates
   * for that site.
   */
  lazy val ExpectCt: String = "Expect-CT"

  /**
   * Provides a mechanism to allow and deny the use of browser features in a website's own frame, and in iframes that it
   * embeds.
   */
  lazy val PermissionsPolicy: String = "Permissions-Policy"

  /**
   * Force communication using HTTPS instead of HTTP.
   */
  lazy val StrictTransportSecurity: String = "Strict-Transport-Security"

  /**
   * Sends a signal to the server expressing the client's preference for an encrypted and authenticated response, and
   * that it can successfully handle the upgrade-Insecure-Requests directive.
   */
  lazy val UpgradeInsecureRequests: String = "Upgrade-Insecure-Requests"

  /**
   * Disables MIME sniffing and forces browser to use the type given in Content-Type.
   */
  lazy val XContentTypeOptions: String = "X-Content-Type-Options"

  /**
   * Indicates whether a browser should be allowed to render a page in a frame, iframe, embed or object.
   */
  lazy val XFrameOptions: String = "X-Frame-Options"

  /**
   * Specifies if a cross-Domain policy file (crossdomain.xml) is allowed. The file may define a policy to grant
   * clients, such as Adobe's Flash Player (now obsolete), Adobe Acrobat, Microsoft Silverlight (now obsolete),
   * or Apache Flex, permission to handle data across domains that would otherwise be restricted due to the Same-Origin
   * Policy. See the Cross-Domain Policy File Specification for more information.
   */
  lazy val XPermittedCrossDomainPolicies: String = "X-Permitted-Cross-Domain-Policies"

  /**
   * May be set by hosting environments or other frameworks and contains information about them while not providing any
   * usefulness to the application or its visitors. Unset this header to avoid exposing potential vulnerabilities.
   */
  lazy val XPoweredBy: String = "X-Powered-By"

  /**
   * Enables cross-Site scripting filtering.
   */
  lazy val XXssProtection: String = "X-Xss-Protection"

  /**
   * Indicates the relationship between a request initiator's origin and its target's origin. It is a Structured Header
   * whose value is a token with possible values cross-Site, same-Origin, same-Site, and none.
   */
  lazy val SecFetchSite: String = "Sec-Fetch-Site"

  /**
   * Indicates the request's mode to a server. It is a Structured Header whose value is a token with possible values
   * cors, navigate, no-Cors, same-Origin, and websocket.
   */
  lazy val SecFetchMode: String = "Sec-Fetch-Mode"

  /**
   * Indicates whether or not a navigation request was triggered by user activation. It is a Structured Header whose
   * value is a boolean so possible values are ?0 for false and ?1 for true.
   */
  lazy val SecFetchUser: String = "Sec-Fetch-User"

  /**
   * Indicates the request's destination. It is a Structured Header whose value is a token with possible values audio,
   * audioworklet, document, embed, empty, font, image, manifest, object, paintworklet, report, script, serviceworker,
   * sharedworker, style, track, video, worker, and xslt.
   */
  lazy val SecFetchDest: String = "Sec-Fetch-Dest"

  /**
   * Indicates the purpose of the request, when the purpose is something other than immediate use by the user-Agent.
   * The header currently has one possible value, prefetch, which indicates that the resource is being fetched
   * preemptively for a possible future navigation.
   */
  lazy val SecPurpose: String = "Sec-Purpose"

  /**
   * A request header sent in preemptive request to fetch() a resource during service worker boot. The value, which is
   * set with NavigationPreloadManager.setHeaderValue(), can be used to inform a server that a different resource
   * should be returned than in a normal fetch() operation.
   */
  lazy val ServiceWorkerNavigationPreload: String = "Service-Worker-Navigation-Preload"

  /**
   * Response header used to specify server endpoints where the browser should send warning and error reports when
   * using the Reporting API.
   */
  lazy val ReportingEndpoints: String = "Reporting-Endpoints"

  /**
   * Response header used to specify server endpoints where the browser should send warning and error reports when
   * using the Reporting API.
   */
  lazy val ReportTo: String = "Report-To"

  /**
   * Specifies the form of encoding used to safely transfer the resource to the user.
   */
  lazy val TransferEncoding: String = "Transfer-Encoding"

  /**
   * Specifies the transfer encodings the user agent is willing to accept.
   */
  lazy val Te: String = "TE"

  /**
   * Allows the sender to include additional fields at the end of chunked message.
   */
  lazy val Trailer: String = "Trailer"

  /**
   * Response header that indicates that the server is willing to upgrade to a WebSocket connection.
   */
  lazy val SecWebSocketAccept: String = "Sec-Websocket-Accept"

  /**
   * In requests, this header indicates the WebSocket extensions supported by the client in preferred order. In
   * responses, it indicates the extension selected by the server from the client's preferences.
   */
  lazy val SecWebSocketExtensions: String = "Sec-Websocket-Extensions"

  /**
   * Request header containing a key that verifies that the client explicitly intends to open a WebSocket.
   */
  lazy val SecWebSocketKey: String = "Sec-Websocket-Key"

  /**
   * In requests, this header indicates the sub-Protocols supported by the client in preferred order. In responses, it
   * indicates the the sub-Protocol selected by the server from the client's preferences.
   */
  lazy val SecWebSocketProtocol: String = "Sec-Websocket-Protocol"

  /**
   * In requests, this header indicates the version of the WebSocket protocol used by the client. In responses, it is
   * sent only if the requested protocol version is not supported by the server, and lists the versions that the server
   * supports.
   */
  lazy val SecWebSocketVersion: String = "Sec-Websocket-Version"

  /**
   * Used to list alternate ways to reach this service.
   */
  lazy val AltSvc: String = "Alt-Svc"

  /**
   * Used to identify the alternative service in use.
   */
  lazy val AltUsed: String = "Alt-Used"

  /**
   * Contains the date and time at which the message was originated.
   */
  lazy val Date: String = "Date"

  /**
   * This entity-Header field provides a means for serializing one or more links in HTTP headers. It is semantically
   * equivalent to the HTML link element.
   */
  lazy val Link: String = "Link"

  /**
   * Indicates how long the user agent should wait before making a follow-Up request.
   */
  lazy val RetryAfter: String = "Retry-After"

  /**
   * Communicates one or more metrics and descriptions for the given request-Response cycle.
   */
  lazy val ServerTiming: String = "Server-Timing"

  /**
   * Used to remove the path restriction by including this header in the response of the Service Worker script.
   */
  lazy val ServiceWorkerAllowed: String = "Service-Worker-Allowed"

  /**
   * Links generated code to a source map.
   */
  lazy val SourceMap: String = "Sourcemap"

  /**
   * This HTTP/1.1 (only) header can be used to upgrade an already established client/server connection to a different
   * protocol (over the same transport protocol). For example, it can be used by a client to upgrade a connection from
   * HTTP 1.1 to HTTP 2.0, or an HTTP or HTTPS connection into a WebSocket.
   */
  lazy val Upgrade: String = "Upgrade"

  /**
   * Provides a hint from about the priority of a particular resource request on a particular connection. The value can
   * be sent in a request to indicate the client priority, or in a response if the server chooses to reprioritize the
   * request.
   */
  lazy val Priority: String = "Priority"

  /**
   * Used to indicate that the response corresponding to the current request is eligible to take part in attribution
   * reporting, by registering either an attribution source or trigger.
   */
  lazy val AttributionReportingEligible: String = "Attribution-Reporting-Eligible"

  /**
   * Included as part of a response to a request that included an Attribution-Reporting-Eligible header, this is used
   * to register an attribution source.
   */
  lazy val AttributionReportingRegisterSource: String = "Attribution-Reporting-Register-Source"

  /**
   * Included as part of a response to a request that included an Attribution-Reporting-Eligible header, this is used
   * to register an attribution trigger.
   */
  lazy val AttributionReportingRegisterTrigger: String = "Attribution-Reporting-Register-Trigger"

  /**
   * Servers can advertise support for Client Hints using the Accept-CH header field or an equivalent HTML meta element
   * with http-Equiv attribute.
   */
  lazy val AcceptCh: String = "Accept-CH"

  /**
   * Servers use Critical-CH along with Accept-CH to specify that accepted client hints are also critical client hints.
   */
  lazy val CriticalCh: String = "Critical-CH"

  /**
   * User agent's branding and version.
   */
  lazy val SecChUa: String = "Sec-CH-UA"

  /**
   * User agent's underlying platform architecture.
   */
  lazy val SecChUaArch: String = "Sec-CH-UA-Arch"

  /**
   * User agent's underlying CPU architecture bitness (for example "64" bit).
   */
  lazy val SecChUaBitness: String = "Sec-CH-UA-Bitness"

  /**
   * User agent's form-Factors, describing how the user interacts with the user-Agent.
   */
  lazy val SecChUaFormFactor: String = "Sec-CH-UA-Form-Factor"

  /**
   * User agent's full version string.
   */
  lazy val SecChUaFullVersion: String = "Sec-CH-UA-Full-Version"

  /**
   * Full version for each brand in the user agent's brand list.
   */
  lazy val SecChUaFullVersionList: String = "Sec-CH-UA-Full-Version-List"

  /**
   * User agent is running on a mobile device or, more generally, prefers a "mobile" user experience.
   */
  lazy val SecChUaMobile: String = "Sec-CH-UA-Mobile"

  /**
   * User agent's device model.
   */
  lazy val SecChUaModel: String = "Sec-CH-UA-Model"

  /**
   * User agent's underlying operation system/platform.
   */
  lazy val SecChUaPlatform: String = "Sec-CH-UA-Platform"

  /**
   * User agent's underlying operation system version.
   */
  lazy val SecChUaPlatformVersion: String = "Sec-CH-UA-Platform-Version"

  /**
   * Whether or not the user agent binary is running in 32-Bit mode on 64-Bit Windows.
   */
  lazy val SecChUaWoW64: String = "Sec-CH-UA-WoW64"

  /**
   * User's preference of dark or light color scheme.
   */
  lazy val SecChPrefersColorScheme: String = "Sec-CH-Prefers-Color-Scheme"

  /**
   * User's preference to see fewer animations and content layout shifts.
   */
  lazy val SecChPrefersReducedMotion: String = "Sec-CH-Prefers-Reduced-Motion"

  /**
   * Request header indicates the user agent's preference for reduced transparency.
   */
  lazy val SecChPrefersReducedTransparency: String = "Sec-CH-Prefers-Reduced-Transparency"

  /**
   * Response header used to confirm the image device to pixel ratio (DPR) in requests where the screen DPR client hint
   * was used to select an image resource.
   */
  lazy val ContentDpr: String = "Content-DPR"

  /**
   * Approximate amount of available client RAM memory. This is part of the Device Memory API.
   */
  lazy val DeviceMemory: String = "Device-Memory"

  /**
   * Request header that provides the client device pixel ratio (the number of physical device pixels for each CSS pixel).
   */
  lazy val Dpr: String = "DPR"

  /**
   * Request header provides the client's layout viewport width in CSS pixels.
   */
  lazy val ViewportWidth: String = "Viewport-Width"

  /**
   * Request header indicates the desired resource width in physical pixels (the intrinsic size of an image).
   */
  lazy val Width: String = "Width"

  /**
   * Approximate bandwidth of the client's connection to the server, in Mbps. This is part of the Network Information API.
   */
  lazy val Downlink: String = "Downlink"

  /**
   * The effective connection type ("network profile") that best matches the connection's latency and bandwidth. This
   * is part of the Network Information API.
   */
  lazy val Ect: String = "ECT"

  /**
   * Application layer round trip time (RTT) in milliseconds, which includes the server processing time. This is part of
   * the Network Information API.
   */
  lazy val Rtt: String = "RTT"

  /**
   * A string on that indicates the user agent's preference for reduced data usage.
   */
  lazy val SaveData: String = "Save-Data"

  /**
   * Request header that indicates the user's tracking preference (Do Not Track). Deprecated in favor of
   * Global Privacy Control (GPC), which is communicated to servers using the Sec-GPC header, and accessible to clients
   * via navigator.globalPrivacyControl.
   */
  lazy val Dnt: String = "DNT"

  /**
   * Response header that indicates the tracking status that applied to the corresponding request. Used in conjunction
   * with DNT.
   */
  lazy val Tk: String = "Tk"

  /**
   * Indicates whether the user consents to a website or service selling or sharing their personal information with
   * third parties.
   */
  lazy val SecGpc: String = "Sec-GPC"

  /**
   * Provides a mechanism to allow web applications to isolate their origins.
   */
  lazy val OriginIsolation: String = "Origin-Isolation"

  /**
   * Defines a mechanism that enables developers to declare a network error reporting policy.
   */
  lazy val Nel: String = "NEL"

  /**
   * Response header used to mark topics of interest inferred from a calling site's URL as observed in the response to
   * a request generated by a feature that enables the Topics API.
   */
  lazy val ObserveBrowsingTopics: String = "Observe-Browsing-Topics"

  /**
   * Request header that sends the selected topics for the current user along with the associated request, which are
   * used by an ad tech platform to choose a personalized ad to display.
   */
  lazy val SecBrowsingTopics: String = "Sec-Browsing-Topics"

  /**
   * A client can send the Accept-Signature header field to indicate intention to take advantage of any available
   * signatures and to indicate what kinds of signatures it supports.
   */
  lazy val AcceptSignature: String = "Accept-Signature"

  /**
   * Indicates that the request has been conveyed in TLS early data.
   */
  lazy val EarlyData: String = "Early-Data"

  /**
   * Response header used to indicate that the associated Document should be placed in an origin-Keyed agent cluster.
   * This isolation allows user agents to allocate implementation-Specific resources for agent clusters, such as
   * processes or threads, more efficiently.
   */
  lazy val OriginAgentCluster: String = "Origin-Agent-Cluster"

  /**
   * Response header sent by a federated identity provider (IdP) to set its login status, meaning whether any users
   * are logged into the IdP on the current browser or not. This is stored by the browser and used by the FedCM API.
   */
  lazy val SetLogin: String = "Set-Login"

  /**
   * The Signature header field conveys a list of signatures for an exchange, each one accompanied by information about
   * how to determine the authority of and refresh that signature.
   */
  lazy val Signature: String = "Signature"

  /**
   * The Signed-Headers header field identifies an ordered list of response header fields to include in a signature.
   */
  lazy val SignedHeaders: String = "Signed-Headers"

  /**
   * Provides a list of URLs pointing to text resources containing speculation rule JSON definitions. When the response
   * is an HTML document, these rules will be added to the document's speculation rule set.
   */
  lazy val SpeculationRules: String = "Speculation-Rules"

  /**
   * Set by a navigation target to opt-In to using various higher-Risk loading modes. For example, cross-Origin,
   * same-Site prerendering requires a Supports-Loading-Mode value of credentialed-Prerender.
   */
  lazy val SupportsLoadingMode: String = "Supports-Loading-Mode"

  /**
   * Identifies the originating IP addresses of a client connecting to a web server through an HTTP proxy or a load
   * balancer.
   */
  lazy val XForwardedFor: String = "X-Forwarded-For"

  /**
   * Identifies the original host requested that a client used to connect to your proxy or load balancer.
   */
  lazy val XForwardedHost: String = "X-Forwarded-Host"

  /**
   * Identifies the protocol (HTTP or HTTPS) that a client used to connect to your proxy or load balancer.
   */
  lazy val XForwardedProto: String = "X-Forwarded-Proto"

  /**
   * Controls DNS prefetching, a feature by which browsers proactively perform domain name resolution on both links
   * that the user may choose to follow as well as URLs for items referenced by the document, including images, CSS,
   * JavaScript, and so forth.
   */
  lazy val XDnsPrefetchControl: String = "X-Dns-Prefetch-Control"

  /**
   * The X-Robots-Tag HTTP header is used to indicate how a web page is to be indexed within public search engine
   * results. The header is effectively equivalent to meta name="robots" content="…".
   */
  lazy val XRobotsTag: String = "X-Robots-Tag"

  /**
   * Implementation-specific header that may have various effects anywhere along the request-response chain. Used for
   * backwards compatibility with HTTP/1.0 caches where the Cache-Control header is not yet present.
   */
  lazy val Pragma: String = "Pragma"

  /**
   * General warning information about possible problems.
   */
  lazy val Warning: String = "Warning"
  