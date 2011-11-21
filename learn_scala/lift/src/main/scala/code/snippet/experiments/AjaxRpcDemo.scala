package code.snippet.experiments

import net.liftweb._
import common.Full
import http._
import net.liftweb.http.js._
import http.js.JE.JsRaw
import http.js.jquery.JqWiringSupport
import JE._
import JsCmds._
import json.JsonAST.JValue
import json.{JsonAST, Serialization, Extraction, DefaultFormats}
import util._
import Helpers._
import java.util.Date
import java.lang.Thread
import xml.{Node, Elem, Text, NodeSeq}
import java.io.StringWriter

object AjaxRpcDemo {
  implicit val formats = DefaultFormats

  /**
   * Replace the 'rpc.process' function with one that will invoke something on the server.
   */
  def alterMethod(in: NodeSeq) = {
    val fn: (Any) => JsCmd = {
      arg: Any =>

      // TODO: don't know how to get at original json (?!)
        val jv = Extraction.decompose(arg)

        val result = processJson(jv)
        JsExp.jValueToJsExp(result)
    }

    val (call, functDefn) = S.buildJsonFunc(fn)

    // TODO: This isn't working... it is still ajax... async callback.
    val newProcess = JsRaw("function(param) {return " + call.funcId + "(param);}")
    Script(functDefn & JsCmds.SetExp(JE.JsVar("rpc.process"), newProcess))
  }

  def processJson(jv: JValue): JValue = {
    val p = Extraction.extract[Param](jv)
    val r = process(p)
    Extraction.decompose(r)
  }

  /**
   * The fancy remote procedure
   */
  def process(p: Param): Result = {
    val len = p.s.length()
    val diff = len - p.origin
    Result(len, diff)
  }

  case class Param(s: String, origin: Int)

  case class Result(len: Int, diff: Int)

}