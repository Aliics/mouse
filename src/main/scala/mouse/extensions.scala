package mouse

import scala.annotation.targetName

extension (m: Method)
  @targetName("andMethod")
  def &(m1: Method): RouteMatcher =
    RouteMatcher(
      allowedMethods = Seq(m, m1),
      routeParts = Nil,
    )

  @targetName("join")
  def /(p: RoutePart): RouteMatcher =
    RouteMatcher(
      allowedMethods = Seq(m),
      routeParts = Seq(p),
    )

extension (ms: Seq[Method])
  @targetName("join")
  def /(p: RoutePart): RouteMatcher =
    RouteMatcher(
      allowedMethods = ms,
      routeParts = Seq(p),
    )

extension (rm: RouteMatcher)
  @targetName("andMethod")
  def &(m: Method): RouteMatcher =
    rm.copy(allowedMethods = rm.allowedMethods :+ m)

  @targetName("join")
  def /(p: RoutePart): RouteMatcher =
    rm.copy(routeParts = rm.routeParts :+ p)
