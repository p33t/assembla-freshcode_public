package code.snippet.form

import net.liftweb._
import http.S
import wizard._
object FormWizard extends Wizard {
  val screen1 = new Screen {
    val message1 = field("Message One:", "<one>", valMinLen(6, "Must be >= 6 chars"))
  }
  val screen2 = new Screen {
    val message2 = field("Message Two:", "<two>", valMinLen(6, "Must be >= 6 chars"))
  }

  def finish() = {
    S.notice("1: " + screen1.message1.is)
    S.notice("2: " + screen2.message2.is)
  }
}
