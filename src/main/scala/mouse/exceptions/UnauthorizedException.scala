package mouse.exceptions

/**
 * Throwing this exception while handling a [[mouse.Route]] will cause the response to respond with an [[mouse.StatusCode.Unauthorized]].
 */
case class UnauthorizedException(message: String) extends Exception(message)
