package pkg.xhr

import net.liftweb._
import http.rest.RestHelper
import json._
import common._
import util._
import net.liftweb.json.JsonAST.{JInt, JArray}

/**
 * This is not used anywhere.  Seems better to use RPC.
 */
object StatelessDispatchTable extends RestHelper {
  serve(List("xhr") prefix {
    // This works.  Navigate to ../xhr/smoke
    case List("smoke") JsonGet _ => JArray(List(JInt(1), JInt(2), JInt(3)))
  })
}
