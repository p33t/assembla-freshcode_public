package pkg.script

import org.scalatest.Suite
import org.testng.annotations.Test
import net.liftweb.json.{JsonParser, JsonDSL, JsonAST}
import javax.script.ScriptEngineManager

@Test
class ScriptTypesTest extends Suite {
  val js = new ScriptEngineManager().getEngineByMimeType("text/javascript")

  def testTypes() {
    checkReturnVal("'hello';", "hello")
    checkReturnVal("99.9;", 99.9)
  }

  private def checkReturnVal(script: String, expected: Any) {
    expect(expected) {
      js.eval(script)
    }
  }
}