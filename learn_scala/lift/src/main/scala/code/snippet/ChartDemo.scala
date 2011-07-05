package code.snippet

import net.liftweb._
import http.js.JE.JsRaw
import http.js.jquery.JqWiringSupport
import http.js.{JsCmd, JsCmds}
import JsCmds._
import http.{WiringUI, SHtml}
import json.JsonAST.JValue
import json.{JsonAST, Serialization, Extraction, DefaultFormats}
import util._
import Helpers._
import java.util.Date
import java.lang.Thread
import xml.{Node, Elem, Text, NodeSeq}
import java.io.StringWriter

object ChartDemo {
  private implicit val formats = DefaultFormats // for Json conversion

  def fetchData() = {
    val (_, invoker) = SHtml.ajaxInvoke {
      () =>
        Thread.sleep(800)
        val data = formatDataD(1.0, 1.0, 2.0, 1.0, 1.0)
        assignDataD(data) &
        JsCmds.Run("renderChart()")
    }
    val onClick = (invoker & JsReturn(false)).toJsCmd
    "href=# [onclick]" #> onClick
  }

  def defaultData(in: NodeSeq): NodeSeq = {
    val data = formatDataD(3.0, 3.0, 3.0, 3.0, 3.0)
    Script(assignDataD(data))
  }

  private def formatDataD(v00: Double, v025: Double, v05: Double, v075: Double, v10: Double): List[List[Double]] = {
    List(List(0.0, v00), List(0.25, v025), List(0.5, v05), List(0.75, v075), List(1.0, v10))
  }

  private def assignDataD(data: Any): JsCmd = {
    JsRaw("dataD = " + dataDString(data))
  }

  private def dataDString(data: Any): String = {
    //causes an exception... Serialization.write(dataD())
    val json = Extraction.decompose(data)
    val doc = JsonAST.render(json)
    val writer = new StringWriter()
    doc.format(80, writer)
    writer.flush()
    writer.toString
  }
}
