package code.snippet

import net.liftweb._
import http.js.jquery.JqWiringSupport
import http.{WiringUI, SHtml}
import util._
import Helpers._
import xml.Text

object AjaxDemo {
  val feedbackCell = ValueCell("default message");

  def feedback() = {
    "*" #> WiringUI.toNode(feedbackCell, JqWiringSupport.fade)((str, in) => <p>{Text(str)}</p>)
  }

  def basic() = {
    "* ^^" #> "ignored"
  }
}