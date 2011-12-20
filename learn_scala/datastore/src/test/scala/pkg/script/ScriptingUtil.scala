package pkg.script

import net.liftweb.json.JsonAST._
import sun.org.mozilla.javascript.internal._
import com.sun.script.javascript.RhinoScriptEngine
import javax.script.{ScriptEngine, ScriptEngineManager}

object ScriptingUtil {
  lazy val JsFactory = {
    import scala.collection.JavaConversions._
    val factories = new ScriptEngineManager().getEngineFactories
    factories.find(_.getMimeTypes.contains("text/javascript")).get
  }

  def newEngine() = JsFactory.getScriptEngine.asInstanceOf[RhinoScriptEngine]

  def obtainScope(js: ScriptEngine) = {
    // this is really dodgy but I can't find any other way.
    val arr = js.eval("[];").asInstanceOf[Scriptable]
    arr.getParentScope
  }

  def astToJs(jv: JValue, scope: Scriptable): Any = {
    def toJs(a: Any) = Context.javaToJS(a, scope)
    jv match {
      case JString(s) => toJs(s)
      case JInt(i) => toJs(i)
      case JDouble(d) => toJs(d)
      case _ => null
    }
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