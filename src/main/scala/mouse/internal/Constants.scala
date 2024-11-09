package mouse.internal

import mouse.types.Version

private[mouse] object Constants:
  lazy val DefaultVersion: Version = Version(1, 1)

  lazy val HttpPrefix = "http://"

  lazy val HostHeader = "Host"
  lazy val ContentLengthHeader = "Content-Length"
