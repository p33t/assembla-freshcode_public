package pkg

import org.scalatest.Suite
import net.liftweb.json.JsonAST._
import net.liftweb.json.Extraction._
import net.liftweb.json.Printer._

/**
 * Go look at the scala-datastore project for json handling...
 * https://subversion.assembla.com/svn/freshcode_public/learn_scala/datastore
 */
class JsonTest_NOT extends Suite {
  implicit val formats = net.liftweb.json.DefaultFormats
  val desired = """{"one":1,"two":2,"three":[3,3,3]}"""

  def testSerializeMap() {
    val m = Map("one" -> 1, "two" -> 2, "three" -> List(3, 3, 3))
    check(m)
  }

  def testSerializeCaseClass() {
    val m = MyClass(1, 2, List(3, 3, 3))
    check(m)
  }

  def testSerializeTuple() {
    val t = (1, 2)
    // No joy... this produces: {"_1$mcI$sp":1,"_2$mcI$sp":2}
    println(compact(render(decompose(t))))
  }

  private def check(m: Any) {
    expect(desired) {compact(render(decompose(m)))}
  }
}

/**
 * Note this must be declared as a top level class to be json serialized cleanly by automatic means.
 */
case class MyClass(one: Int, two: Int, three: List[Int])
