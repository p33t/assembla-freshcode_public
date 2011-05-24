package code.snippet

import net.liftweb._
import http.js.jquery.JqWiringSupport
import http.js.{JsCmd, JsCmds}
import JsCmds._
import http.{WiringUI, SHtml}
import util._
import Helpers._
import xml.Text
import java.util.Date

object AjaxDemo {
  // TODO: Change this to XML type (not string :)
  val feedbackCell = ValueCell("default message");

  def feedback() = {
    "*" #> WiringUI.toNode(feedbackCell, JqWiringSupport.fade)((str, in) => <p>
      {Text(str)}
    </p>)
  }

  def basic() = {
    val invoke = SHtml.ajaxInvoke {
      () =>
        feedbackCell.set("You clicked at" + new Date)
        Noop
    }
    "href=# [onclick]" #> invoke._2.toJsCmd //List(Alert("Yay!"), JsReturn(false)).toJsCmd
  }
}