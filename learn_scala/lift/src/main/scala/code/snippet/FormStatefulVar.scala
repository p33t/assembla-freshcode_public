package code.snippet

import net.liftweb._
import http._
import util.Helpers._

// AN alternative to FormStateful... can be an 'object' with no parent class
object FormStatefulVar {

  private object message extends RequestVar("")

  private object previousMessage extends RequestVar("")

  def render = {
    // This has an extra hidden field!
    "name=message" #> (SHtml.textElem(message) ++ SHtml.hidden(previousMessage.set(_), previousMessage.is)) &
      "type=submit" #> SHtml.onSubmitUnit {
        () =>
          S.notice("Previous message: " + previousMessage.is)
          S.notice("Current message: " + message.is)
          previousMessage.set(message.is)
      }
  }
}