package mouse

import mouse.exceptions.BadRequestException

object QueryParams {
  def apply(): QueryParams = Map()

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
    req
      .queryParams
      .get(key)
      .map { param =>
        try {
          implicitly[FromParam[P]].fromParam(param)
        } catch {
          case _: IllegalArgumentException =>
            throw BadRequestException(s"""Query parameter "$key" could not be parsed.""")
        }
      }
}
