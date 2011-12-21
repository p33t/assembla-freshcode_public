package pkg.script

import org.scalatest.Suite
import net.liftweb.json.JsonAST._
import ScriptingUtil._
import util.Random
import net.liftweb.json.JsonParser
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class AstToJsTest extends Suite {
  val js = newEngine()

  def testSimple() {
    checkSymmetry(JString("String" + Random.nextInt()))
    checkSymmetry(JInt(Random.nextInt()))
    checkSymmetry(JDouble(Random.nextDouble()))
    checkSymmetry(JBool(Random.nextBoolean()))
    checkSymmetry(JNull) // not sure about this one
    checkSymmetry(JArray(List(JString("hello"), JInt(99))))
    checkSymmetry(JObject(List(JField("one", JInt(1)), JField("two", JString("due")))))
  }

  def testComplex() {
    val json = JsonParser.parse("""[
      {"bruce": "lee", "springsteen": 99},
      99,
      [
        "hi how are you",
        3.14
      ]
    ]""")
    checkSymmetry(json)
    check(json, "jv[2][0]", "hi how are you")
    check(json, "jv[0].springsteen", 99)
  }

  // TODO: This needs to work... maybe its a prototype thing?
  def testJsFunctions_BAD() {
    val arr = JsonParser.parse("""["one", "two"]""")
    val tfot = JsonParser.parse("""["three", "four", "one", "two"]""")
    val expr = """["three", "four"].concat(jv)"""
    val result = eval(arr, expr)
    val actual = jsToAst(result)
    expect(tfot)(actual)
    // TODO: Other way round
  }

  private def eval(jv: JValue, expr: String): AnyRef = {
    val binds = js.createBindings()
    js.getContext
    binds.put("jv", astToJs(jv))
    js.eval(expr + ";", binds)
  }

  private def check(jv: JValue, expr: String, expected: Any) {
    expect(expected) {
      eval(jv, expr)
    }
  }

  private def checkSymmetry(jv: JValue) {
    expect(jv) {
      val output: AnyRef = eval(jv, "jv")
      jsToAst(output)
    }
  }
}
