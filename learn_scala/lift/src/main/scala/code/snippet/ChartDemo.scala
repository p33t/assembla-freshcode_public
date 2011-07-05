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
  def defaultData(in: NodeSeq): NodeSeq = {
    Script(JsRaw("dataD = " + dataDString()))
  }

  private def dataDString(): String = {
    //causes an exception... Serialization.write(dataD())
    val doc = JsonAST.render(dataD())
    val writer = new StringWriter()
    doc.format(80, writer)
    writer.flush()
    writer.toString
  }

  private def dataD(): JValue = {
    val v = List(List(0.0, 3.0), List(0.25, 3.0), List(0.5, 1.0), List(0.75, 3.0), List(1.0, 3.0))
    Extraction.decompose(v)
  }
}
