package pkg.json

import net.liftweb.json._
import org.junit.runner.RunWith
import org.scalatest.Spec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class JsonMutationTest extends Spec {
  def testMutation() {
    val before = parse("""{
      "series": [
        {"dataId": "1"},
        {"dataId": "2"}
      ]
    }""")
    val expected = parse("""{
      "series": [
        {"data":1},
        {"data":2}
      ]
    }""")
    assertResult(expected){
      before.transform {
        case JField("dataId", dataId: JString) => JField("data", JInt(dataId.values.toInt))
      }
    }
  }
}