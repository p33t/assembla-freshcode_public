package pkg.script

import org.scalatest.Suite
import org.testng.annotations.Test
import net.liftweb.json.{JsonParser, JsonDSL, JsonAST}
import javax.script.ScriptEngineManager
import net.liftweb.json.JsonAST._
import sun.org.mozilla.javascript.internal.{NativeObject, Scriptable, Context, NativeArray}

@Test
class ScriptTypesTest extends Suite {
  val js = new ScriptEngineManager().getEngineByMimeType("text/javascript")

  def testTypes() {
    checkReturnVal("'hello';", JString("hello"))
    checkReturnVal("99.9;", JDouble(99.9))
// No...    checkReturnVal("99;", JInt(99))
    checkReturnVal("[1, 'two']", JArray(List(JDouble(1), JString("two"))))

    //    js.getContext
    //    val ctx = Context.enter()
    //    ctx.isSealed

    //    No.
    //    val arr = new NativeArray(2L)
    //    arr.put(0, Native 1.0)
    //    arr.put(1, 2.0)
    //    checkReturnVal("[1,2];", arr)
    //    No.
    //    checkReturnVal("[1,2];", new NativeArray(
    //      Array[AnyRef](1.0.asInstanceOf[AnyRef], 2.0.asInstanceOf[AnyRef])
    //    ))

    //    No.
    //    checkReturnVal("[1,2,3,4];", Array[java.lang.Object](1, 2, 3, 4))
  }

  private def convert(o: Any): JValue = {
    o match {
      case s: String => JString(s)
      case i: BigInt => JInt(i)
      case d: Double => JDouble(d)
      case arr: NativeArray =>
        val elems = (0 until arr.getLength.toInt).map {
          ix =>
            val elem = arr.get(ix, arr)
            convert(elem)
        }.toList
        JArray(elems)
//      case obj: NativeObject =>
//        val fields = (0 until obj.)
    }
  }

  private def checkReturnVal(script: String, expected: Any) {
    val result = js.eval(script)
    val converted = convert(result)
    expect(expected) {
      converted
    }
  }
}