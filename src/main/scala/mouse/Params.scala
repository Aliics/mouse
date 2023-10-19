package mouse

import mouse.exceptions.BadRequestException

object Params {
  def apply(): Params = Map()

  def required[P: FromParam](key: String)(implicit req: Request): P =
    optional(key).getOrElse(throw BadRequestException(s"""Query parameter "$key" must be provided"""))

  def optional[P: FromParam](key: String)(implicit req: Request): Option[P] =
    req.params
      .get(key)
      .map { param =>
        try {
          implicitly[FromParam[P]].fromParam(param)
        } catch {
          case _: IllegalArgumentException =>
            throw BadRequestException(s"""Query parameter "$key" could not be parsed.""")
        }
      }

  trait FromParam[P] {
    def fromParam(param: String): P
  }
  trait FromParamDefaults {
    implicit lazy val FromParamString: FromParam[String] = (param: String) => param
    implicit lazy val FromParamCharArray: FromParam[Array[Char]] = (param: String) => param.toCharArray
    implicit lazy val FromParamBoolean: FromParam[Boolean] = (param: String) => param.toBoolean
    implicit lazy val FromParamByte: FromParam[Byte] = (param: String) => param.toByte
    implicit lazy val FromPartShort: FromParam[Short] = (param: String) => param.toShort
    implicit lazy val FromParamInt: FromParam[Int] = (param: String) => param.toInt
    implicit lazy val FromParamLong: FromParam[Long] = (param: String) => param.toLong
    implicit lazy val FromParamFloat: FromParam[Float] = (param: String) => param.toFloat
    implicit lazy val FromParamDouble: FromParam[Double] = (param: String) => param.toDouble
  }
}
