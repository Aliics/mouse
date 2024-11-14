package mouse

import mouse.types.HeaderValues

import scala.language.implicitConversions

/**
 * Transform a simple headers map into a [[HeaderValues]] case class.
 * This class allows for better extraction of header data.
 *
 * @param headers Headers on a [[Request]] or [[Response]]
 * @return
 */
implicit def headersToHeaderValues(headers: Map[String, String]): HeaderValues =
  HeaderValues(headers)
