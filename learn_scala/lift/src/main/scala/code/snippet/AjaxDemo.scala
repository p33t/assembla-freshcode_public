package code.snippet

import net.liftweb._
import common.Empty
import http.js.JE.JsRaw
import http.js.jquery.JqWiringSupport
import http.js.JsCmds.CmdPair
import http.js.{JsCmd, JsCmds}
import net.liftweb.http.js.JsCmds._
import http.{WiringUI, SHtml}
import util._
import Helpers._
import xml.Text

object AjaxDemo {
  // TODO: Change this to XML type (not string :)
  val feedbackCell = ValueCell("default message");

  def feedback() = {
    "*" #> WiringUI.toNode(feedbackCell, JqWiringSupport.fade)((str, in) => <p>
      {Text(str)}
    </p>)
  }

  def basic() = {
    "href=# [onclick]" #>  List(Alert("Yay!"), JsReturn(false)).toJsCmd
  }
}