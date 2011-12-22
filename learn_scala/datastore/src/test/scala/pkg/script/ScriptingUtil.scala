package pkg.script

import net.liftweb.json.JsonAST._
import javax.script.{ScriptEngine, ScriptEngineManager}
import sun.org.mozilla.javascript.internal._
import com.sun.script.javascript.{RhinoTopLevel, RhinoScriptEngine}
import util.Random

object ScriptingUtil {
  lazy val JsFactory = {
    import scala.collection.JavaConversions._
    val factories = new ScriptEngineManager().getEngineFactories
    factories.find(_.getMimeTypes.contains("text/javascript")).get
  }

  def newEngine() = JsFactory.getScriptEngine

  /**
   * Enables creation of Rhino native objects with access to a 'scope'.
   */
  class VarCreator(varMap: Map[String, JValue]) {
    /**
     * Called from within the script.  EG: creator.create([]);
     */
    def create(parentScopeProvider: Scriptable) {
      val scope = parentScopeProvider.getParentScope
      varMap.foreach {
        t2 =>
          val (name, jv) = t2
          val jsVal = astToJs(jv, scope)
          // weird, but that's the way it is done
          scope.put(name, scope, jsVal)
      }
    }
  }

  /**
   * Convert a JsonAst Element to a JavaScript compatible object.
   */
  def astToJs(jv: JValue, scope: Scriptable): Any = {
    def toJs(a: Any) = Context.javaToJS(a, scope)
    jv match {
      case JString(s) => toJs(s)
      case JInt(i) => toJs(i)
      case JDouble(d) => toJs(d)
      case JBool(b) => toJs(b)
      //      case JNull => toJs(null) // RuntimeException: No Context associated with current Thread
      case JArray(elems) =>
        val arr = elems.toArray.map(astToJs(_, scope).asInstanceOf[Object])
        val na = new NativeArray(arr)
        na.setPrototype(ScriptableObject.getClassPrototype(scope, "Array"))
        na
      case JObject(fields) =>
        val obj = new NativeObject()
        // TODO: Figure out what test would require this line of code
        obj.setPrototype(ScriptableObject.getClassPrototype(scope, "Object"))
        fields.foreach {
          f =>
            obj.put(f.name, obj, astToJs(f.value, scope))
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
}