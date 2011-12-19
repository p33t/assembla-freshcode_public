package pkg.script

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.Suite
import javax.script.{Invocable, Compilable, ScriptException, ScriptEngineManager}
import ScriptingTest._
import sun.org.mozilla.javascript.internal.EvaluatorException
import scala.collection.JavaConversions._

@RunWith(classOf[JUnitRunner])
class ScriptingTest extends Suite {
  // alternative... val js = new ScriptEngineManager().getEngineByMimeType("text/javascript")
  val factory = new ScriptEngineManager().getEngineFactories.find(_.getMimeTypes.contains("text/javascript")).get
  val js = factory.getScriptEngine

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
    expect("bruce") {js.eval("if (true) 'bruce'; else 'lee';")}
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

  def testInvoke() {
    val comp = js.asInstanceOf[Compilable]
    // compiled for speed (?)
    // NOTE: Still not sure how to use this.
    val script = comp.compile("""
      function hello(arg) {
        return arg;
      }
    """)

    // run the script
    script.eval()

    val inv = script.getEngine.asInstanceOf[Invocable]
    expect("bruce") {
      inv.invokeFunction("hello", "bruce")
    }
  }

  def testNamedArgs_BAD() {
    // named args do not work...unsuprisingly
    intercept[EvaluatorException] {
      val one = MyClass(1, "one")
      val binds = js.createBindings()
      binds.put("one", one)
      expect(MyClass(1, "uno")) {
        js.eval("""
      one.copy({stringVal: "uno"});
    """, binds)
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
