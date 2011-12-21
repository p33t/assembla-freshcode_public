package pkg.script

import org.scalatest.Suite
import net.liftweb.json.JsonAST._
import ScriptingUtil._
import util.Random

class AstToJsTest extends Suite {
  val js = newEngine()
  val scope = obtainScope(js)
  println("Obtainted scope " + scope)
  def test() {
    check(JString("String" + Random.nextInt()))
    check(JInt(Random.nextInt()))
    check(JDouble(Random.nextDouble()))
    check(JBool(Random.nextBoolean()))
    check(JNull) // not sure about this one
    check(JArray(List(JString("hello"), JInt(99))))
    check(JObject(List(JField("one", JInt(1)), JField("two", JString("due")))))
  }

  private def check(jv: JValue) {
    expect(jv) {
      val binds = js.createBindings()
      js.getContext
      binds.put("myVar", astToJs(jv, scope))
      val output = js.eval("myVar;", binds)
      jsToAst(output)
    }
  }
}
