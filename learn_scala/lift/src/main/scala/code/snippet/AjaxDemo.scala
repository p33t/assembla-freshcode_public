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

  def multi() = {
    // TODO: Figure out line breaks.
    val elems = Range(1, 10)
    "href=#" #> {
      (in: NodeSeq) =>
        require(in.length == 1, "Only one href=# element expected.")
        elems.flatMap {
          elem =>
            ("* *" #> Text(" Item " + elem) & "* [onclick]" #> onclick("You clicked on " + elem))(in)
        }
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