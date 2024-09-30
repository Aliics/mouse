package mouse

enum Status(code: Int):
  case Ok extends Status(200)
  case NotFound extends Status(404)
  case InternalServerError extends Status(500)

  override def toString: String =
    this match
      case Status.Ok => "200 OK"
      case Status.NotFound => "404 Not Found"
      case Status.InternalServerError => "500 Internal Server Error"
