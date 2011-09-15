package pkg

import org.scalatest.Suite
import org.testng.annotations.Test
import net.liftweb.json.{Extraction, TypeInfo, Formats, DefaultFormats}
import net.liftweb.json.Extraction._
import net.liftweb.json.Printer._
import net.liftweb.json.JsonAST._

@Test
class JsonDeserializeTest extends Suite {
  val TestParent = Parent("one", "two", Child("three"))

  def testSerialize() {
    implicit val JsonFormats = DefaultFormats // + Parent.Serializer // for Json conversion
    val str = compact(render(decompose(TestParent)))
    println(str)
  }

}

case class Parent(f1: String, f2: String, f3: Child)

case class Child(f: String)

object Parent {

  object Serializer extends net.liftweb.json.Serializer[Parent] {
    def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), Parent] = Map.empty

    def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
      case p: Parent => Extraction.decompose(List(p.f2, p.f1))(format)
    }
  }

}
