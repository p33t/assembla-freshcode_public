package pkg.json.versions

import org.scalatest.Suite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import net.liftweb.json.{DefaultFormats, JsonParser, Extraction}

@RunWith(classOf[JUnitRunner])
class MyModelSerializerTest extends Suite {
  implicit val formats = DefaultFormats.withHints(VersionHints) ++ List(MyModelSerializer)

  // TODO: Deserialize

  def testSerialize() {
    val json = JsonParser.parse("""{"jsonClass": "v2", "elems": {"one": {"num": 1}, "two": {"num": 2}}}""")
    expect(json) {
      Extraction.decompose(MyModel(Map(
        "one" -> Elem(1),
        "two" -> Elem(2)
      )))
    }
  }
}