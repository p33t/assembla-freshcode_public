package code.lib.extjsinterop

import net.liftweb._
import http._
import http.rest.RestHelper
import js.{JsExp, JsCmds, JsCmd, JE}
import json._
import common._
import util._
import BasicTypesHelpers._
import net.liftweb.json.{Extraction, JsonAST}
import Extraction._
import code.lib.SimpleRest
import java.nio.charset.Charset
import collection.JavaConverters._

object Crud extends RestHelper with Loggable {
  private val db = new NumStrMap

  serve("extjsinterop" :: Nil prefix {
    case Post("c" :: Nil, req) =>
      NotImplementedResponse()
    case Get("r" :: Nil, req) =>
      log(req)
      val data = db.values()
      respond(req, decompose(CrudResponse(data)))
    case Post("u" :: Nil, req) =>
      NotImplementedResponse()
    case Post("d" :: Nil, req) =>
      NotImplementedResponse()
  })

  private def log(req: Req) {
    val body = req.body.getOrElse(Array())
    val str = String.valueOf(body.map(_.toChar))
    logger.warn("params: " + req.params + "\nbody: " + str)
  }

  /**
   * Create a formal jsonp response that delivers the given result
   */
  private def respond(req: Req, result: => JValue) = {
    def response(callback: String) = {
      val call = JE.Call(callback, JsExp.jValueToJsExp(result))
      logger.warn("Responding with " + call.toJsCmd)
      jsExpToResp(call)
    }

    req.param("callback")
      .map(response)
      .getOrElse(BadResponse())
  }


//  /**
//   * Extract the requst arguments as a json object.
//   */
//  private def paramsToJson(req: Req): JObject = {
//    val fields = req.params.map {
//      t2 =>
//        val (name, values) = t2
//        val ja = JArray(values.map(paramValueToJson))
//        JField(name, ja)
//    }
//    JObject(fields.toList)
//  }

  /**
   * Converts a parameter value to a JValue.
   * Currently implemented as a stock JsonParser.parse() but this may need to be more elaborate.
   */
  private def paramValueToJson(value: String): JValue = {
    JsonParser.parse(value)
  }

  case class CrudResponse(success: Boolean, message: Option[String], data: Option[Any])

  object CrudResponse {
    val SuccessOnly = CrudResponse(true, None, None)

    def apply(message: String, success: Boolean = false): CrudResponse = CrudResponse(success, Some(message), None)

    def apply(data: Any): CrudResponse = CrudResponse(true, None, Some(data))
  }

}