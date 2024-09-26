package mouse

import mouse.errors.ParseError

case class Version(major: Int, minor: Int)

object Version:
  def apply(s: String): Either[ParseError, Version] =
    s match
      case s"HTTP/$mj.$mn" =>
        (for
          major <- mj.toIntOption
          minor <- mn.toIntOption
        yield Version(major, minor))
          .fold
          (Left(ParseError(s"Invalid request version: $s"))) // No version from mapping. Error.
          (Right(_)) // We could turn the version numbers into integers. Success.
