package pkg.json

import net.liftweb.json._
import org.junit.runner.RunWith
import org.scalatest.Spec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class JsonDeserializeTest extends Spec {
  implicit val JsonFormats = DefaultFormats + RestrictedSerializer // for Json conversion
  val StringParent = Parent("three", "four", Child("five"), new StringChild("six"))
  val VoidParent = Parent("one", "two", Child("three"), new VoidChild)


  def testSerialize() {
    def checkParent(p: Parent) {
      val str = Printer.compact(render(Extraction.decompose(p)))
      val actual = parse(str).extract[Parent]
      assertResult(p)(actual)
    }
    checkParent(StringParent)
    checkParent(VoidParent)
  }

  def testIgnoreExtraData() {
    val ast = Extraction.decompose(StringParent).asInstanceOf[JObject]
    val astExtra = JObject(JField("extraField", JString("ignored data")) :: ast.obj)
    assertResult(StringParent) {
      astExtra.extract[Parent]
    }
  }

  def testPartialDeserialize() {
    case class C(f: String, rest: JArray) // NOTE: JValue also works here
    val expected = C("bruce", JArray(List(JString("one"), JString("two"), JString("three"))))
    // Doesn't work...Extraction.decompose(expected)
    val ast = parse("""{"f":"bruce", "rest":["one","two","three"]}""")
    assertResult(expected) {
      ast.extract[C]
    }
  }
}
