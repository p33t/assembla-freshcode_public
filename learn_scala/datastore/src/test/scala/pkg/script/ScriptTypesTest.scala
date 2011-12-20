package pkg.script

import org.scalatest.Suite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import net.liftweb.json.JsonAST._
import ScriptingUtil._

@RunWith(classOf[JUnitRunner])
class ScriptTypesTest extends Suite {
  val js = JsFactory.getScriptEngine

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

  private def checkReturnVal(script: String, expected: Any) {
    val result = js.eval(script)
    val converted = jsToAst(result)
    expect(expected) {
      converted
    }
  }
}