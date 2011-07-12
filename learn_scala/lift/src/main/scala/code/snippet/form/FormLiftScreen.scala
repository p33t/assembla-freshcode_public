package code.snippet.form

import net.liftweb._
import util._
import net.liftweb.http.{S, LiftScreen}
import net.liftweb.util.FieldError
import Helpers._

object FormLiftScreen extends LiftScreen {
  // Need only declare the field and any restrictions
  val message = field("Message", "", validateMessage _)

  def finish() {
    S.notice("Message is: " + message.is)
  }

  private def validateMessage(s: String): List[FieldError] = {
    var result = valMinLen(5, "Must be >= 5 chars")(s)
    if (asInt(s).isDefined) result = FieldError(message, "Message cannot be a number") :: result
    result
  }
}