package pkg.json

import org.scalatest.Suite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import net.liftweb.json._

@RunWith(classOf[JUnitRunner])
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