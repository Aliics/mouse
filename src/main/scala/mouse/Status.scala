package mouse

enum Status(code: Int):
  case Ok extends Status(200)
  
  override def toString: String =
    this match
      case Status.Ok => "200 OK"
