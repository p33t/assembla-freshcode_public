package pkg.json

import java.io.InputStreamReader

import net.liftweb.json.Extraction._
import net.liftweb.json.JsonAST._
import net.liftweb.json.Printer._
import net.liftweb.json.{JsonParser, MappingException}
import org.junit.runner.RunWith
import org.scalatest.Spec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class JsonFileTest extends Spec {
  implicit val formats = net.liftweb.json.DefaultFormats
  val JsonFileName = "simple.json"
  val Expected = Map(
    "elem1" -> Map("name" -> "elem1 name",
      "value" -> "elem1 value",
      "list" -> List("one", "two", "three")
    ), "elem2" -> Map("name" -> "elem2 name"))

  def testLoadFile() {
    val contents = loadResourceFile(JsonFileName)
    val actualBox = JsonParser.parse(contents)
    val actual = actualBox.values
    assertResult(Expected)(actual)
  }

  def testLoadFileTooHard() {
    val stream = ClassLoader.getSystemResourceAsStream(JsonFileName)
    val jval = JsonParser.parse(new InputStreamReader(stream))
    val s = compact(render(jval))
    println(s)
    // some error saying 'No information known about type'
    intercept[MappingException] {
      extract[Map[String, Any]](jval)
    }
  }

  private def loadResourceFile(fileName: String): String = {
    val stream = ClassLoader.getSystemResourceAsStream(fileName)
    try {
      io.Source.fromInputStream(stream).mkString
    }
    finally {
      stream.close()
    }
  }
}
