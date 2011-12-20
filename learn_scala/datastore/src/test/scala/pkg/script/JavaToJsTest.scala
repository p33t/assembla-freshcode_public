package pkg.script

import org.scalatest.Suite

class JavaToJsTest extends Suite {
  val js = ScriptingUtil.JsFactory.getScriptEngine
  def test() {
    val expected = "hello"
    expect(expected) {
      js.eval("'hello';")
    }
  }
}