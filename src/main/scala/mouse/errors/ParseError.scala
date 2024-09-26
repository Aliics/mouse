package mouse.errors

case class ParseError(message: String) extends Exception(message)
