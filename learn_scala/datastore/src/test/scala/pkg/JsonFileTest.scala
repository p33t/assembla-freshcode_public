package pkg

import org.testng.annotations.Test
import io.Source
import net.liftweb.json.JsonAST._
import net.liftweb.json.Extraction._
import net.liftweb.json.Printer._
import org.scalatest.Suite
import net.liftweb.util.JSONParser
import java.io.{InputStream, InputStreamReader, StringReader}
import net.liftweb.json.{MappingException, JsonParser}

@Test
class JsonFileTest extends Suite {
  implicit val formats = net.liftweb.json.DefaultFormats
  val JsonFileName = "simple.json"
  val Expected = Map(
    "elem1" -> Map("name" -> "elem1 name",
      "value" -> "elem1 value",
      "list" -> List("one", "two", "three")
    ), "elem2" -> Map("name" -> "elem2 name"))

  def testLoadFile() {
    val stream = ClassLoader.getSystemResourceAsStream(JsonFileName)

    // this way gets an error about unknown types
    //    val jval = JsonParser.parse(new InputStreamReader(stream))
    //    val s = compact(render(jval))
    //    println(s)
    //extract[Map[String, Any]](jval)

    val actualBox = parseStream(stream)

    val actual = actualBox.get
    expect(Expected)(actual)
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

  private def parseStream(stream: InputStream) = {
    try {
      JSONParser.parse(io.Source.fromInputStream(stream).mkString)
    }
    finally {
      stream.close()
    }
  }
}