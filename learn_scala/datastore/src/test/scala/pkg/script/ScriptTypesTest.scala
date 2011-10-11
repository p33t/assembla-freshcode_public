package pkg.script

import org.scalatest.Suite
import org.testng.annotations.Test
import net.liftweb.json.{JsonParser, JsonDSL, JsonAST}
import javax.script.ScriptEngineManager
import sun.org.mozilla.javascript.internal.NativeArray

@Test
class ScriptTypesTest extends Suite {
  val js = new ScriptEngineManager().getEngineByMimeType("text/javascript")

  def testTypes() {
    checkReturnVal("'hello';", "hello")
    checkReturnVal("99.9;", 99.9)
    checkReturnVal("99;", 99.0)
//    checkReturnVal("[1,2];", new NativeArray(
//      Array[AnyRef](1.0.asInstanceOf[AnyRef], 2.0.asInstanceOf[AnyRef])
//    ))
//    checkReturnVal("[1,2,3,4];", Array[java.lang.Object](1, 2, 3, 4))
  }

  private def checkReturnVal(script: String, expected: Any) {
    val result = js.eval(script)
    expect(expected) {
      result
    }
  }
}