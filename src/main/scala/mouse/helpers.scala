package mouse

private[mouse] def tryToEither[L, R](f: => R, l: String => L): Either[L, R] =
  try
    Right(f)
  catch
    case e: Throwable => Left(l(e.getMessage))
