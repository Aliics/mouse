package mouse.errors

case class UnknownParamNameError(name: String) extends Exception(s"Attempted to access unregistered param: $name")
