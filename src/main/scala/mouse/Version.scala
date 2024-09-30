package mouse

import mouse.errors.ParseError

case class Version(major: Int, minor: Int):
  override def toString: String = s"HTTP/$major.$minor"

object Version:
  /**
   * Parse HTTP version from a [[String]]. Format is HTTP/$major.$major.
   * @param s The HTTP Version string.
   * @return The [[Version]] or parsing error message.
   */
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
