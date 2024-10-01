package mouse

import scala.annotation.targetName

extension (m: Method)
  @targetName("andMethod")
  inline def &(m1: Method): RouteMatcher =
    RouteMatcher(
      allowedMethods = Seq(m, m1),
      routeParts = Nil,
    )

  @targetName("join")
  inline def /(p: RoutePart): RouteMatcher =
    RouteMatcher(
      allowedMethods = Seq(m),
      routeParts = Seq(p),
    )

extension (ms: Seq[Method])
  @targetName("join")
  inline def /(p: RoutePart): RouteMatcher =
    RouteMatcher(
      allowedMethods = ms,
      routeParts = Seq(p),
    )

extension (rm: RouteMatcher)
  @targetName("andMethod")
  inline def &(m: Method): RouteMatcher =
    rm.copy(allowedMethods = rm.allowedMethods :+ m)

  @targetName("join")
  inline def /(p: RoutePart): RouteMatcher =
    rm.copy(routeParts = rm.routeParts :+ p)

extension (req: Request)
  inline def fromQuery(key: String, inline or: => String): String =
    given Request = req
    queryParam(key, or)

  inline def fromQuery(key: String): Option[String] =
    given Request = req
    queryParam(key)

  inline def fromRoute(key: String): String =
    given Request = req
    routeParam(key)
