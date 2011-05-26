package code.snippet

import net.liftweb._
import http.js.jquery.JqWiringSupport
import http.js.{JsCmd, JsCmds}
import JsCmds._
import http.{WiringUI, SHtml}
import util._
import Helpers._
import java.util.Date
import java.lang.Thread
import xml.{Text, NodeSeq}

object AjaxDemo {
  val feedbackCell = ValueCell(<p>default message</p>);

  def feedback() = {
    "*" #> WiringUI.toNode(feedbackCell, JqWiringSupport.fade)((xml, in) => xml)
  }

  def basic() = {
    "href=# [onclick]" #> onclick("You clicked at " + new Date)
  }

  def multi(in: NodeSeq): NodeSeq = {
    val elems = Range(1, 10)

    elems.flatMap {
      elem =>
        val render = "href=#" #> (
          "* [onclick]" #> onclick("You clicked on " + elem) &
            "* *" #> ("Item " + elem.toString)
          )
        render(in)
    }
  }

  private def onclick(msg: String): String = {
    val (_, invoker) = SHtml.ajaxInvoke {
      () =>
        Thread.sleep(800)
        feedbackCell.set(<p>
          {Text(msg)}
        </p>)
        Noop
    }
    invoker
    List[JsCmd](invoker, JsReturn(false)).toJsCmd
  }
}