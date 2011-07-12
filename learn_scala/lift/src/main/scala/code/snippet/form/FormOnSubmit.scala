package code.snippet.form

import net.liftweb._
import common.Full
import http._
import util.Helpers._

object FormOnSubmit {
  // NOTE: This cannot return a NodeSeq nor can it take a NodeSeq arg.
  def render = {
    S.notice("Form Rendered")
    // This is not remembered between form requests.
    var numbers: List[String] = Nil
    var number = ""

    def process() {
      asInt(number) match {
        case Full(n) =>
          numbers = number :: numbers
          S.notice("Numbers so far: " + numbers)
        case _ =>
          S.notice("'" + number + "' is not a number")
          S.redirectTo("/form/on-submit.html")
      }
    }

    "name=number" #> SHtml.onSubmit(number = _) &
      "type=submit" #> SHtml.onSubmitUnit(process _)
  }
}