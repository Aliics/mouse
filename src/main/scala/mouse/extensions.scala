package mouse

import java.net.URI
import scala.annotation.targetName

extension (method: Method)
  @targetName("join")
  def /(uri: String): RouteMatcher = (m, u) =>
    m == method && u.toString.stripPrefix("/") == uri

extension (method: RouteMatcher)
  @targetName("join")
  def /(uri: String): RouteMatcher = (m, u) =>
    val us = u.toString
    method(m, URI.create(us dropRight uri.length + 1)) && (us `endsWith` uri)
