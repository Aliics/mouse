package mouse.internal

import mouse.errors.ParseError

import java.io.InputStream
import scala.annotation.tailrec

private[mouse] class InputParser(inputStream: InputStream):
  /**
   * Recursively searches for the needle to occur when reading from [[inputStream]]. When the needle is found, reading
   * stops. If we exhaust all the data from the stream, we simply stop reading.
   *
   * @param needle The string to look for from the [[inputStream]].
   * @param acc    Accumulating string from the [[inputStream]].
   * @return [[acc]]
   */
  @tailrec
  private[mouse] final def readUntil(needle: String, acc: String = ""): String =
    val c = inputStream.read()
    if c == -1 then acc // Exhausted input stream data.
    else
      val haystack = acc + c.toChar
      if haystack `endsWith` needle then haystack dropRight needle.length // We found our result!
      else readUntil(needle, haystack) // We haven't found our needle yet, keep searching.

  /**
   * Recursively read from [[inputStream]] until there are no more header lines.
   *
   * @return Map of header key-value pairs.
   */
  private[mouse] def parseHeaders: Either[ParseError, Map[String, String]] =
    readUntil("\r\n") match
      case s"$k: $v" => parseHeaders.map(Map(k -> v) ++ _) // Standard header line.
      case "" => Right(Map.empty) // Nothing was on the same line.
      case s => Left(ParseError(s"Invalid header format: $s"))
