package pkg

import org.testng.annotations.Test
import org.scalatest.Suite
import javax.script.{ScriptException, ScriptEngineManager}

@Test
class ScriptingTest extends Suite {
  def testJsIsPresent() {
    val em = new ScriptEngineManager()
    val engine = em.getEngineByMimeType("text/javascript")
    assert(engine != null)
  }

  def testSimpleScript() {
    val script = """
      callback.output('Hello from the script');
      var s = myClass.stringVal() + ' - ' + myClass.intVal();
      callback.output('You gave me ' + s);
      """
    val js = new ScriptEngineManager().getEngineByMimeType("text/javascript")
    val binds = js.createBindings()
    binds.put("callback", ScriptingTest.Callback)
    binds.put("myClass", ScriptingTest.MyClass(3, "three"))
    js.eval(script, binds)
  }

  def testErrorCondition() {
    val js = new ScriptEngineManager().getEngineByMimeType("text/javascript")
    intercept[ScriptException] {
      js.eval("bruce;")
    }
  }

  def testReturns() {
    val js = new ScriptEngineManager().getEngineByMimeType("text/javascript")
    expect("springsteen") {js.eval("'springsteen';")}
  }
}

object ScriptingTest {

  case class MyClass(intVal: Int, stringVal: String)

  object Callback {
    def output(s: String) {
      println(s)
    }
  }

}
