package pkg.script

import javax.script.ScriptEngineManager
import sun.org.mozilla.javascript.internal.{NativeObject, NativeArray}
import net.liftweb.json.JsonAST._

object ScriptingUtil {
  lazy val JsFactory = {
    import scala.collection.JavaConversions._
    val factories = new ScriptEngineManager().getEngineFactories
    factories.find(_.getMimeTypes.contains("text/javascript")).get
  }

  /**
   * Maybe this should use Context.jsToJava()
   */
  def jsToAst(o: Any): JValue = {
    o match {
      case null => JNull
      case b: Boolean => JBool(b)
      case s: String => JString(s)
      case i: BigInt => JInt(i)
      case d: Double => JDouble(d)
      case arr: NativeArray =>
        val elems = (0 until arr.getLength.toInt).map {
          ix =>
            val elem = arr.get(ix, arr)
            jsToAst(elem)
        }.toList
        JArray(elems)
      case obj: NativeObject =>
        val ids = obj.getIds.toList
        val fields = ids.map {
          fieldObj =>
            val fieldName = fieldObj.toString
            val fieldValue = obj.get(fieldName, obj)
            JField(fieldName, jsToAst(fieldValue))
        }
        JObject(fields)
    }
  }
}