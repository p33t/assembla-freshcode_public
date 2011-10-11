package pkg.script

import org.testng.annotations.Test
import org.scalatest.Suite
import javax.script.{ScriptException, ScriptEngineManager}

@Test
class ScriptingTest extends Suite {
  val js = new ScriptEngineManager().getEngineByMimeType("text/javascript")

  def testJsIsPresent() {
    assert(js != null)
  }

  def testSimpleScript() {
    val script = """
      callback.output('Hello from the script');
      var s = myClass.stringVal() + ' - ' + myClass.intVal();
      callback.output('You gave me ' + s);
      """
    val binds = js.createBindings()
    binds.put("callback", ScriptingTest.Callback)
    binds.put("myClass", ScriptingTest.MyClass(3, "three"))
    js.eval(script, binds)
  }

  def testErrorCondition() {
    intercept[ScriptException] {
      js.eval("bruce;")
    }
    intercept[ScriptException] {
      js.eval("throw 'augh';")
    }
  }

  def testReturns() {
    expect("springsteen") {js.eval("'springsteen';")}
    // not allowed to use 'return' keyword outside a function in a script.
    expect("bruce"){js.eval("if (true) 'bruce'; else 'lee';")}
  }

  def testApply_BAD() {
    val binds = js.createBindings()
    binds.put("callback", ScriptingTest.Callback)
    // It's a NativeJavaObject... not a function.
    intercept[ScriptException] {
      expect(ScriptingTest.Callback("bruce lee")) {
        js.eval("""callback('bruce lee');""", binds)
      }
    }
  }
}

object ScriptingTest {

  case class MyClass(intVal: Int, stringVal: String)

  object Callback {
    def output(s: String) {
      println(s)
    }

    def apply(s: String) = {
      s.reverse
    }
  }

}
