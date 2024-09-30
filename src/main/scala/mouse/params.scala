package mouse

import java.net.URLDecoder
import java.nio.charset.StandardCharsets

/**
 * Extract a query param from the [[Request]]'s [[URI]].
 * All parameters values are [[String]]s, and are presumed to use UTF-8 encoding.
 *
 * This is just a wrapper for [[queryParam()]], but it handles the [[None]] case.
 *
 * For readability, it is recommended to add the second parameter as a named argument:
 * {{{
 *   val name = queryParam("name", or = "Ollie")
 * }}}
 *
 * @param key Matching key of the parameter.
 * @param or A default value. Lazily evaluated.
 * @return The found parameter or the default.
 */
@inline def queryParam(key: String, or: => String)(using Request): String =
  queryParam(key) getOrElse or

/**
 * Extract a query param from the [[Request]]'s [[URI]].
 * All parameters values are [[String]]s, and are presumed to use UTF-8 encoding.
 *
 * @param key Matching key of the parameter.
 * @param req [[Request]] needed as context.
 * @return
 */
def queryParam(key: String)(using req: Request): Option[String] =
  for
    q <- Option(req.uri.getQuery) // Java nullability, we need to wrap it in an Option.
    v <- q
      .split("&") // Initial "?" is not present, so we don't need to fuss about that.
      .collectFirst:
        case s"$k=$v" if k == key => v // Only match if the key is strictly matched.
      .map(URLDecoder.decode(_, StandardCharsets.UTF_8))
  yield v
