package mouse

import mouse.exceptions.BadRequestException

object Params {
  def apply(): Params = Map()

  /**
   * Receive a param by its key when handling a [[Request]].
   *
   * This is non-optional, and it will throw a [[exceptions.BadRequestException]] if the param is not on the request.
   *
   * If the given value is malformed, a [[exceptions.BadRequestException]] will be thrown.
   *
   * @param key Query parameter key.
   * @param req [[Request]] in scope. Usually in the context of handling on a [[Route]].
   * @tparam P A [[FromParam]] trait in scope. Used for parsing the given parameter value.
   * @return The parameter value.
   */
  def required[P: FromParam](key: String)(implicit req: Request): P =
    optional(key).getOrElse(throw BadRequestException(s"""Query parameter "$key" must be provided"""))

  /**
   * Optionally receive a param by its key when handling a [[Request]].
   *
   * If the given value is malformed, a [[exceptions.BadRequestException]] will be thrown.
   *
   * @param key Query parameter key.
   * @param req [[Request]] in scope. Usually in the context of handling on a [[Route]].
   * @tparam P A [[FromParam]] trait in scope. Used for parsing the given parameter value.
   * @return An optional parameter value.
   */
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

    /**
     * Comma-separated values which can be individually parsed to a list.
     *
     * @tparam P Item type which is parsable using FromParam[P]
     */
    implicit def FromParamList[P: FromParam]: FromParam[List[P]] = (param: String) =>
      param
        .split(",")
        .toList
        .map(implicitly[FromParam[P]].fromParam(_))
  }
}
