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

/**
 * A more complete Ajax demo with data being passed from the browser AND
 * data is asynchronously being returned to the browser.
 */
object AjaxRpcDemo {
  implicit val formats = DefaultFormats

  /**
   * Replace the 'rpc.process' function with one that will invoke something on the server.
   */
  def alterMethod(in: NodeSeq) = {
    // NOTE: There are variants for specifying error handling.
    val (call, functDefn) = S.buildJsonFunc {
      arg: Any =>
      // TODO: don't know how to get at original json (?!)
      // So just undo any extracting that has happened so far (arg is a map).
        val jv = Extraction.decompose(arg)

        val result = processJson(jv)
        Thread.sleep(800)

        // Define how the result is handled in the browser.
        JE.Call("rpc.succeeded", JsExp.jValueToJsExp(result));
    }

    val replaceProcess = JsRaw("rpc.process = function(param) {" + call.funcId + "(param);};")
    Script(functDefn & replaceProcess)
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