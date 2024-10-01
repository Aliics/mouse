package mouse.types

import mouse.errors.ParseError

enum Method:
  case Delete
  case Get
  case Options
  case Post
  case Put

  override def toString: String =
    this match
      case Method.Delete => "DELETE"
      case Method.Get => "GET"
      case Method.Options => "OPTIONS"
      case Method.Post => "POST"
      case Method.Put => "PUT"

object Method:
  /**
   * Get method from a [[String]]. Case-sensitive.
   *
   * @param s Input string (e.g. GET)
   * @return The corresponding [[Method]].
   */
  def apply(s: String): Either[ParseError, Method] =
    s match
      case "DELETE" => Right(Delete)
      case "GET" => Right(Get)
      case "OPTIONS" => Right(Options)
      case "POST" => Right(Post)
      case "PUT" => Right(Put)
      case _ => Left(ParseError(s"Invalid request method: $s"))
