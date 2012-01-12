package code.snippet.form

import net.liftweb._
import util._
import net.liftweb.http.{S, LiftScreen}
import net.liftweb.util.FieldError
import Helpers._

object FormLiftScreen extends LiftScreen {
  // TODO: Don't know how to do a ScreenVar field. (?)
  val key = field("Key", "")
  // Need only declare the field and any restrictions
  val message = field("Message", "", validateMessage _)

  def finish() {
    S.notice("Message is: " + message.is + ", key:" + key.is)
  }

  override protected def localSetup() {
    key.set(readKey())
    super.localSetup()
  }

  private def validateMessage(s: String): List[FieldError] = {
    var result = valMinLen(5, "Must be >= 5 chars")(s)
    if (asInt(s).isDefined) result = FieldError(message, "Message cannot be a number") :: result
    result
  }

  private def readKey() = {
    println("Reading the key...")
    val box = S.param("key")
    if (box.isEmpty) {
      println("no key found")
      S.notice("Key has not been supplied.")
      redirectTo("/form")
      "no-key"
    }
    else {
      println("key found: " + box.get)
      box.get
    }
  }
}