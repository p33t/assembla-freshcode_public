package pkg.script

import org.scalatest.Suite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import net.liftweb.json.JsonAST._
import javax.script.ScriptEngineManager
import sun.org.mozilla.javascript.internal.{NativeObject, Scriptable, Context, NativeArray}
import net.liftweb.json.Printer

@RunWith(classOf[JUnitRunner])
class ScriptTypesTest extends Suite {
  val js = new ScriptEngineManager().getEngineByMimeType("text/javascript")

  def testTypes() {
    checkReturnVal("'hello';", JString("hello"))
    checkReturnVal("true;", JBool(true))
    checkReturnVal("null;", JNull)
    checkReturnVal("undefined;", JNull)
    checkReturnVal(";", JNull)
    checkReturnVal("99.9;", JDouble(99.9))
    // No...    checkReturnVal("99;", JInt(99))
    checkReturnVal("[1, 'two'];", JArray(List(JDouble(1), JString("two"))))
    checkReturnVal("var x = {'one': 'uno', 'two': 'due', 'three': [3], 'four': {'quatro': '4'}}; x;",
      JObject(List(JField("one", JString("uno")),
        JField("two", JString("due")),
        JField("three", JArray(List(JDouble(3)))),
        JField("four", JObject(List(JField("quatro", JString("4")))))
      )))
  }

  private def convert(o: Any): JValue = {
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
            convert(elem)
        }.toList
        JArray(elems)
      case obj: NativeObject =>
        val ids = obj.getIds.toList
        val fields = ids.map {
          fieldObj =>
            val fieldName = fieldObj.toString
            val fieldValue = obj.get(fieldName, obj)
            JField(fieldName, convert(fieldValue))
        }
        JObject(fields)
    }
  }

  private def checkReturnVal(script: String, expected: Any) {
    val result = js.eval(script)
    val converted = convert(result)
    expect(expected) {
      converted
    }
  }
}