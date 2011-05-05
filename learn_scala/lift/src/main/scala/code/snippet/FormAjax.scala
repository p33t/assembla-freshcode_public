package code.snippet

import net.liftweb._
import http._
import js._
import JsCmds._
import util._
import Helpers._


object FormAjax {
  def render = {
    var message = ""
    val referer = S.referer openOr "/"
    def process: JsCmd = {

      // pause for effect
      Thread.sleep(500)

      if (message.length < 5) {
        S.error("message", "Message must be >= 5 chars: " + message)
        Noop
      }
      else RedirectTo(referer, () => S.notice("Message: " + message))
    }

    "name=message" #> (SHtml.text(message, message = _) ++ SHtml.hidden(process _))
  }
}