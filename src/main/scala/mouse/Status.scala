package mouse

enum Status(code: Int):
  case Ok extends Status(200)
  case NotFound extends Status(404)

  override def toString: String =
    this match
      case Status.Ok => "200 OK"
      case Status.NotFound => "404 Not Found"
