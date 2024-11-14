package mouse

import mouse.types.HeaderValues

import scala.language.implicitConversions

implicit def headersToHeaderValues(headers: Map[String, String]): HeaderValues =
  HeaderValues(headers)
