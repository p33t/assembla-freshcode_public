package pkg.script

import net.liftweb.json.JsonAST._
import com.sun.script.javascript.RhinoScriptEngine
import javax.script.{ScriptEngine, ScriptEngineManager}
import sun.org.mozilla.javascript.internal._

object ScriptingUtil {
  lazy val JsFactory = {
    import scala.collection.JavaConversions._
    val factories = new ScriptEngineManager().getEngineFactories
    factories.find(_.getMimeTypes.contains("text/javascript")).get
  }

  def newEngine() = JsFactory.getScriptEngine

  // This does not appear to be necessary... just use 'null'?!
  def obtainScope(js: ScriptEngine) = {
    // Doesn't work...     js.eval("[].getParentScope();").asInstanceOf[Scriptable]

    // this is really dodgy but I can't find any other way.
    val arr = js.eval("[];").asInstanceOf[Scriptable]
    val s = arr.getParentScope
    require(s != null, "No scope available.")
    s
  }

  /**
   * Convert a JsonAst Element to a JavaScript compatible object.
   */
  def astToJs(jv: JValue): Any = {
    def toJs(a: Any) = Context.javaToJS(a, null)
    jv match {
      case JString(s) => toJs(s)
      case JInt(i) => toJs(i)
      case JDouble(d) => toJs(d)
      case JBool(b) => toJs(b)
      //      case JNull => toJs(null) // RuntimeException: No Context associated with current Thread
      case JArray(elems) =>
        val arr = elems.toArray.map(astToJs(_).asInstanceOf[Object])
        new NativeArray(arr)
      case JObject(fields) =>
        val obj = new NativeObject()
        fields.foreach {
          f =>
            obj.put(f.name, obj, astToJs(f.value))
        }
        obj
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
      case _ =>
        // used for break points
        throw new MatchError(o);
    }
  }

  //  class ArrayAdapter(arr: JArray) extends ScriptableObject {
  //    /**
  //     * The Java method defining the JavaScript resetCounter function.
  //     *
  //     * Resets the counter to 0.
  //     */
  //    def jsFunction_resetCounter: Unit = {
  //      counter = 0
  //    }
  //
  //    /**
  //     * The Java method implementing the getter for the counter property.
  //     * <p>
  //     * If "setCounter" had been defined in this class, the runtime would
  //     * call the setter when the property is assigned to.
  //     */
  //    def jsGet_counter: Int = {
  //      return ({counter += 1; counter})
  //    }
  //  }

}