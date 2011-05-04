package code.snippet

import net.liftweb.http.{S, LiftScreen}

object FormLiftScreen extends LiftScreen {
  // Need only declare the field and any restrictions
  val message = field("Message", "", valMinLen(5, "Must be >= 5 chars"))
  def finish() {
    S.notice("Message is: " + message.is)
  }
}