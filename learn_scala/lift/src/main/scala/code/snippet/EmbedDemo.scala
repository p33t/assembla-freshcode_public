package code.snippet

import net.liftweb.util._
import Helpers._

object EmbedDemo {
  val blank: Option[String] = None
  val removeIds = "* [id]" #> blank

  def nested = {
    // TODO: Use some sort of argument from the top level host page
    "#insertBelow *" #> "Replacement Dynamic Content" &
      removeIds
  }
}