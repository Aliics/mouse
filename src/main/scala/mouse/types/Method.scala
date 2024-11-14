package mouse.types

import mouse.errors.ParseError

/**
 * All HTTP Methods defined as an enum.
 * 
 * Methods will be used to define routes on a [[mouse.Server]], or when building a [[mouse.types.Request]]. In the
 * case of a building a request, it's recommended to use one of the helper methods on the [[mouse.Client]] instead. 
 *
 * Reference: [[https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods]]
 */
enum Method:
  /**
   * The CONNECT method establishes a tunnel to the server identified by the target resource.
   */
  case Connect

  /**
   * The DELETE method deletes the specified resource.
   */
  case Delete

  /**
   * The GET method requests a representation of the specified resource.
   * Requests using GET should only retrieve data and should not contain a request content.
   */
  case Get

  /**
   * The HEAD method asks for a response identical to a GET request, but without a response body.
   */
  case Head

  /**
   * The OPTIONS method describes the communication options for the target resource.
   */
  case Options

  /**
   * The PATCH method applies partial modifications to a resource.
   */
  case Patch
  
  /**
   * The POST method submits an entity to the specified resource,
   * often causing a change in state or side effects on the server.
   */
  case Post

  /**
   * The PUT method replaces all current representations of the target resource with the request content.
   */
  case Put

  /**
   * The TRACE method performs a message loop-back test along the path to the target resource.
   */
  case Trace

  override def toString: String =
    this match
      case Connect => "CONNECT"
      case Delete => "DELETE"
      case Get => "GET"
      case Head => "HEAD"
      case Options => "OPTIONS"
      case Patch => "PATCH"
      case Post => "POST"
      case Put => "PUT"
      case Trace => "TRACE"

/**
 * All HTTP Methods defined as an enum.
 *
 * Methods will be used to define routes on a [[mouse.Server]], or when building a [[mouse.types.Request]]. In the
 * case of a building a request, it's recommended to use one of the helper methods on the [[mouse.Client]] instead.
 *
 * Reference: [[https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods]]
 */
object Method:
  /**
   * Expressive name for [[Method.values]], while functionally equivalent, it will better document to users that the
   * route utilizing this accepts all [[Method]]s.
   */
  lazy val All: Array[Method] = Method.values

  /**
   * Get method from a [[String]]. Case-sensitive.
   *
   * @param s Input string (e.g. GET)
   * @return The corresponding [[Method]].
   */
  def apply(s: String): Either[ParseError, Method] =
    s match
      case "CONNECT" => Right(Connect)
      case "DELETE" => Right(Delete)
      case "GET" => Right(Get)
      case "HEAD" => Right(Head)
      case "OPTIONS" => Right(Options)
      case "PATCH" => Right(Patch)
      case "POST" => Right(Post)
      case "PUT" => Right(Put)
      case "TRACE" => Right(Trace)
      case _ => Left(ParseError(s"Invalid request method: $s"))
