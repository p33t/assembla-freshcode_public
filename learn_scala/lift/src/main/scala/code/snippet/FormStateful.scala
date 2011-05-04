package code.snippet

import net.liftweb._
import http._
import util.Helpers._

// StatefulSnippet cannot be an 'object' and must have 'dispatch' method
// Injects an extra field in form so that original instance can be located again
class FormStateful extends StatefulSnippet {
  private var messages: List[String] = Nil
  private var message = ""
  // original origin
  private val origin = S.referer openOr "/forms.html"

  def dispatch = {
    case "render" => render
  }

  private def process() {
    if (message.length < 3) S.error("'" + message + "' is too short; It must be >= 3 chars")
    else {
      messages = message :: messages
    }
    S.notice("Messages so far: " + messages)
  }

  def render = {
    // renders a <input type="text"... element
    "name=message" #> SHtml.text(message, message = _, "id" -> "some_extra_attribute_as_necessary") &
      "type=submit" #> SHtml.onSubmitUnit(process _)
  }
}