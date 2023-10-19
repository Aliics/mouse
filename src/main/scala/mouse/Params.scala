package mouse

import mouse.exceptions.BadRequestException

object Params {
  def apply(): Params = Map()

  def required(key: String)(implicit req: Request): String =
    req.params.getOrElse(key, throw BadRequestException(s"Query parameter \"$key\" must be provided"))
  def optional(key: String)(implicit req: Request): Option[String] = req.params.get(key)
}
