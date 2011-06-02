package code.snippet

import net.liftweb.util._
import Helpers._

object EmbedDemo {
  val blank: Option[String] = None
  val removeIds = "* [id]" #> blank

  def nested = {
    // TODO: Use some sort of argument
    "#insertBelow *" #> "Replacement Dynamic Content" &
      removeIds
  }
}