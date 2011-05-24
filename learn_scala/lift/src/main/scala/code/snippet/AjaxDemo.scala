package code.snippet

import net.liftweb._
import http.js.jquery.JqWiringSupport
import http.js.{JsCmd, JsCmds}
import JsCmds._
import http.{WiringUI, SHtml}
import util._
import Helpers._
import java.util.Date
import xml.Text
import java.lang.Thread

object AjaxDemo {
  val feedbackCell = ValueCell(<p>default message</p>);

  def feedback() = {
    "*" #> WiringUI.toNode(feedbackCell, JqWiringSupport.fade)((xml, in) => xml)
  }

  def basic() = {
    val (_, invoker) = SHtml.ajaxInvoke {
      () =>
        Thread.sleep(800)
        feedbackCell.set(<p>{Text("You clicked at " + new Date)}</p>)
        Noop
    }
    "href=# [onclick]" #> List[JsCmd](invoker, JsReturn(false)).toJsCmd
  }
}