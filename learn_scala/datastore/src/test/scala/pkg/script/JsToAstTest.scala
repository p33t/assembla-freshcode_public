package pkg.script

import net.liftweb.json.JsonAST._
import org.junit.runner.RunWith
import org.scalatest.Spec
import org.scalatest.junit.JUnitRunner
import pkg.script.ScriptingUtil._

@RunWith(classOf[JUnitRunner])
class JsToAstTest extends Spec {
  val js = JsFactory.getScriptEngine

  def testTypes() {
    check("'hello';", JString("hello"))
    check("true;", JBool(true))
    check("null;", JNull)
    check("undefined;", JNull)
    check(";", JNull)
    check("99.9;", JDouble(99.9))
    // No...    checkReturnVal("99;", JInt(99))
    check("[1, 'two'];", JArray(List(JDouble(1), JString("two"))))
    check("var x = {'one': 'uno', 'two': 'due', 'three': [3], 'four': {'quatro': '4'}}; x;",
      JObject(List(JField("one", JString("uno")),
        JField("two", JString("due")),
        JField("three", JArray(List(JDouble(3)))),
        JField("four", JObject(List(JField("quatro", JString("4")))))
      )))
  }

  private def check(script: String, expected: Any) {
    val result = js.eval(script)
    val converted = jsToAst(result)
    assertResult(expected) {
      converted
    }
  }
}