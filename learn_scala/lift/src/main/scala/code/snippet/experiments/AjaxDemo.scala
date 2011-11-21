package code.snippet.experiments

import net.liftweb._
import http.js.jquery.JqWiringSupport
import http.js.{JsCmd, JsCmds}
import JsCmds._
import http.{WiringUI, SHtml}
import util._
import Helpers._
import java.util.Date
import java.lang.Thread
import xml.{Node, Elem, Text, NodeSeq}

/**
 * NOTE: This is uses comet to get a value back to the browser AND
 * arguments are stored on the server... it doesn't come from the browser.
 * This is merely 'invoking' something on the server.
 */
object AjaxDemo {
  val feedbackCell = ValueCell[NodeSeq](<p>default message</p>);

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

  def tables(in: NodeSeq): NodeSeq = {
    val render = ".cellText *" #> "Custom Cell Text"
    val content = render(in)
    <a href='#' onclick={Text(onclickNode(content))}>Display Table</a>
  }

  def onclickNode(content: NodeSeq): String = {
    val (_, invoker) = SHtml.ajaxInvoke {
      () =>
        Thread.sleep(800)
        feedbackCell.set(content)
        Noop
    }
    List[JsCmd](invoker, JsReturn(false)).toJsCmd
  }

  private def onclick(msg: String): String = {
    val content = <p>{Text(msg)}</p> ++ <div class="lift:embed?what=experiments/_embed_simple"></div>
    onclickNode(content)
  }
}