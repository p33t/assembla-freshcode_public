package pkg.json

import org.scalatest.Suite
import org.testng.annotations.Test
import net.liftweb.json._

@Test
class JsonDeserializeTest extends Suite {
  implicit val JsonFormats = DefaultFormats + RestrictedSerializer // for Json conversion
  val StringParent = Parent("three", "four", Child("five"), new StringChild("six"))
  val VoidParent = Parent("one", "two", Child("three"), new VoidChild)


  def testSerialize() {
    def checkParent(p: Parent) {
      val str = Printer.compact(render(Extraction.decompose(p)))
      val actual = parse(str).extract[Parent]
      expect(p)(actual)
    }
    checkParent(StringParent)
    checkParent(VoidParent)
  }

  def testIgnoreExtraData() {
    val ast = Extraction.decompose(StringParent).asInstanceOf[JObject]
    val astExtra = JObject(JField("extraField", JString("ignored data")) :: ast.obj)
    expect(StringParent) {
      astExtra.extract[Parent]
    }
  }

  def testPartialDeserialize() {
    case class C(f: String, rest: JArray)
    val expected = C("bruce", JArray(List(JString("one"), JString("two"), JString("three"))))
    // Doesn't work...Extraction.decompose(expected)
    val ast = parse("""{"f":"bruce", "rest":["one","two","three"]}""")
    expect(expected) {
      ast.extract[C]
    }
  }
}
