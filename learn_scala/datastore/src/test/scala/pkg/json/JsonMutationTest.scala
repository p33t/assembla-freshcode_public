package pkg.json

import org.scalatest.Suite
import org.testng.annotations.Test
import net.liftweb.json._

@Test
class JsonMutationTest extends Suite {
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
    expect(expected){
      before.transform {
        case JField("dataId", dataId: JString) => JField("data", JInt(dataId.values.toInt))
      }
    }
  }
}