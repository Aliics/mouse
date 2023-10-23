package mouse

object PathParams {
  def apply(): PathParams = Map()

  /**
   * Gets the path parameter from the scoped [[Request]]. This is non-optional, and since this is required from the user
   * it is up to the developer to get the key correct.
   *
   * @param key Key of the path parameter.
   * @param req [[Request]] in scope. Usually in the context of handling on a [[Route]].
   * @tparam P A [[FromParam]] trait in scope. Used for parsing the given parameter value.
   * @return The parameter value.
   */
  def param[P: FromParam](key: String)(implicit req: Request): P =
    implicitly[FromParam[P]].fromParam(req.pathParams(key))
}
