package pkg.script

import org.scalatest.Suite
import net.liftweb.json.JsonAST._
import ScriptingUtil._
import util.Random

class AstToJsTest extends Suite {
  val js = newEngine()
  val scope = obtainScope(js)
  def test() {
    check(JString("String" + Random.nextInt()))
    check(JInt(Random.nextInt()))
    check(JDouble(Random.nextDouble()))
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