package mouse.exceptions

case class BadRequestException(message: String) extends Exception(message)
