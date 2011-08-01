package pkg

import org.scalatest.Suite
import net.liftweb.json.JsonAST._
import net.liftweb.json.Extraction._
import net.liftweb.json.Printer._

class JsonTest extends Suite {
  implicit val formats = net.liftweb.json.DefaultFormats

  def testSerializeMap() {

    val m = Map("one" -> 1, "two" -> 2, "three" -> List(3, 3, 3))
    expect("""{"one":1,"two":2,"three":[3,3,3]}""") {compact(render(decompose(m)))}
  }
}