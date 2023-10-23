package mouse

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
