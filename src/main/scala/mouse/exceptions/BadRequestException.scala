package mouse.exceptions

/**
 * Throwing this exception while handling a [[mouse.Route]] will cause the response to respond with a [[mouse.StatusCode.BadRequest]].
 */
case class BadRequestException(message: String) extends Exception(message)
