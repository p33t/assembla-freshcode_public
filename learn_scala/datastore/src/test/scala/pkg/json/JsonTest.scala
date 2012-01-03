package pkg.json

import org.scalatest.Suite
import net.liftweb.json.JsonAST._
import net.liftweb.json.Extraction._
import net.liftweb.json.Printer._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class JsonTest extends Suite {
  implicit val formats = net.liftweb.json.DefaultFormats
  val desired = """{"one":1,"two":2,"three":[3,3,3]}"""

  def testSerializeMap() {
    val m = Map("one" -> 1, "two" -> 2, "three" -> List(3, 3, 3))
    check(m)
  }

  def testSerializeCaseClass() {
    val m = JsonTest.MyClass(1, 2, List(3, 3, 3))
    check(m)
  }

  def testSerializeTuple() {
    val t = (1, 2)
    // No joy... this produces: {"_1$mcI$sp":1,"_2$mcI$sp":2}
    println(compact(render(decompose(t))))
  }

  def testReplaceDoesNotAdd() {
    val obj = JObject(List(JField("bruce", JString("lee"))))
    expect(obj){
      obj.replace("swamp" :: Nil, JString("thing"))
    }
  }

  private def check(m: Any) {
    expect(desired) {compact(render(decompose(m)))}
  }
}

object JsonTest {
  // NOTE: Cannot be a dependent type.  Class must stand alone.
  case class MyClass(one: Int, two: Int, three: List[Int])
}
