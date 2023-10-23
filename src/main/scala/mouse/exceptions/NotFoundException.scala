package mouse.exceptions

/**
 * Throwing this exception while handling a [[mouse.Route]] will cause the response to respond with a [[mouse.StatusCode.NotFound]].
 */
case class NotFoundException(message: String) extends Exception(message)
