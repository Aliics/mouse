package mouse.types

import mouse.errors.ParseError

/**
 * HTTP version being utilized for [[Request]]/[[Response]] (de)serialization.
 *
 * <b>Note:</b> Only HTTP/1.1 is supported right now.
 */
case class Version(major: Int, minor: Int):
  override def toString: String = s"HTTP/$major.$minor"

/**
 * HTTP version being utilized for [[Request]]/[[Response]] (de)serialization.
 *
 * <b>Note:</b> Only HTTP/1.1 is supported right now.
 */
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
