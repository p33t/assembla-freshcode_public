package pkg.googlereader

import net.liftweb.json._
import collection.immutable.List
import java.util.Date
import java.text.SimpleDateFormat

/**
 * Converts a google reader formatted json to a human readable email body
 */
object App {
  implicit val formats = DefaultFormats
  val DateFormat = new SimpleDateFormat("yyyy-MM-dd")

  def main(args: Array[String]) {
    val json = JsonParser.parse(loadResourceFile("notes.json"))
    val notes = Extraction.extract[Notes](json)
    val myNotes = notes.convert()
    myNotes.foreach {
      mn =>
        println("\n" + mn.title + "\n" + mn.annotation + "\n" + mn.href + "\n" + DateFormat.format (mn.date))
    }
    //    println(Printer.pretty(JsonAST.render(Extraction.decompose(myNotes))))
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

  case class Content(content: Option[String])

  case class Href(href: String)

  case class Note(crawlTimeMsec: String, title: String, alternate: List[Href], content: Content, annotations: List[Content])

  case class Notes(items: List[Note]) {
    def convert() = {
      items.map {
        note =>
          MyNote(note.title, note.alternate(0).href, note.annotations(0).content.get, new Date(note.crawlTimeMsec.toLong))
      }
    }
  }

  case class MyNote(title: String, href: String, annotation: String, date: Date)

}