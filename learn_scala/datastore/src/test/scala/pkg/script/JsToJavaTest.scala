package pkg.script

import org.scalatest.Suite
import ScriptingUtil._
import sun.org.mozilla.javascript.internal.Context

class JsToJavaTest extends Suite {
  val js = newEngine()

  def test() {
    val out = js.eval("{one: 1, two: 'due'};")
    val java = Context.jsToJava(out, classOf[Map[String, Any]])
    expect(Map[String, Any]("one" -> 1, "two" -> "due")) {
      java
    }
  }
}