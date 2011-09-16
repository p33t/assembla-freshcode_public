package pkg.json

import org.scalatest.Suite
import org.testng.annotations.Test
import net.liftweb.json._

@Test
class JsonDeserializeTest extends Suite {
  val TestParents = List(
    Parent("one", "two", Child("three"), new VoidChild),
    Parent("three", "four", Child("five"), new StringChild("six"))
  )

  def testSerialize() {
    implicit val JsonFormats = DefaultFormats + RestrictedSerializer // for Json conversion
    for (p <- TestParents) {
      val str = Printer.compact(render(Extraction.decompose(p)))
      val actual = parse(str).extract[Parent]
      expect(p)(actual)
    }
  }

}
