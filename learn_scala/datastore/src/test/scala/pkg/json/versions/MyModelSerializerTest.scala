package pkg.json.versions

import net.liftweb.json.{DefaultFormats, Extraction, JsonParser}
import org.junit.runner.RunWith
import org.scalatest.Spec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MyModelSerializerTest extends Spec {
  implicit val formats = DefaultFormats.withHints(VersionHints) ++ List(MyModelSerializer)

  // TODO: Deserialize

  def testSerialize() {
    // This is horrible.... I want {"v": 2,...
    val json = JsonParser.parse( """{"jsonClass": "v2", "elems": {"one": {"num": 1}, "two": {"num": 2}}}""")
    assertResult(json) {
      Extraction.decompose(MyModel(Map(
        "one" -> Elem(1),
        "two" -> Elem(2)
      )))
    }
  }
}