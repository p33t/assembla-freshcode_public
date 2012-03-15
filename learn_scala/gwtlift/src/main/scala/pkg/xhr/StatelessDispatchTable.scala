package pkg.xhr

import net.liftweb._
import http.rest.RestHelper
import json._
import common._
import util._
import net.liftweb.json.JsonAST.{JInt, JArray}


object StatelessDispatchTable extends RestHelper {
  serve(List("xhr") prefix {
    case List("smoke") JsonGet _ => JArray(List(JInt(1), JInt(2), JInt(3)))
  })

}