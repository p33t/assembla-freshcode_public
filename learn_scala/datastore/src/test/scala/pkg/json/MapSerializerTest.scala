package pkg.json

import net.liftweb.json._
import org.junit.runner.RunWith
import org.scalatest.Spec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MapSerializerTest extends Spec {
  implicit val formats = DefaultFormats // ++ List(new MapSerializer[Int])

  def testDefaultMapNowPreservesInsertOrder() {
    val tuples = List("one" -> 1, "two" -> 2, "four" -> 4, "three" -> 3)
    assertResult(tuples) {
      Map(tuples: _*).toList
    }
  }

  /**
   * Looks like json parsing retains ordering of fields.  No need for fancy stuff!
   */
  def testJsonOrderRetained() {
    val json = JsonParser.parse("""{"one": 1, "two": 2, "three": 3, "four": 4}""")
    assertResult(json) {
      val m = Extraction.extract[Map[String, Int]](json)
      Extraction.decompose(m)
    }
  }

}

//object MapSerializerTest {
//
//  class MapSerializer[T <: Any : Manifest] extends Serializer[Map[String, T]] {
//    def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), Map[String,T]] = {
//      Map.empty
//    }
//
//    def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
//      Map.empty
//    }
//  }
//
//}
