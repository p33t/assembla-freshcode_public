package pkg.json

import org.scalatest.Suite
import org.testng.annotations.Test
import net.liftweb.json._

@Test
class JsonDeserializeTest extends Suite {


  // TODO: A StringChild example... will need to convert VoidChildSerializer to RestrictedSerializer
  val TestParent = Parent("one", "two", Child("three"), new VoidChild)

  def testSerialize() {
    implicit val JsonFormats = DefaultFormats + VoidChildSerializer // for Json conversion
    val str = Printer.compact(JsonAST.render(Extraction.decompose(TestParent)))
    println(str)
    val actual = JsonParser.parse(str).extract[Parent]
    expect(TestParent)(actual)
  }

}

//object Parent {
//
//  object Serializer extends net.liftweb.json.Serializer[Parent] {
//    def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), Parent] = Map.empty
//
//    def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
//      case p: Parent => Extraction.decompose(List(p.f2, p.f1))(format)
//    }
//  }
//
//}
