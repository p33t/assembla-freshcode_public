package code.snippet.experiments.treeview

import net.liftweb.util._
import Helpers._
import xml._
import scala.None

object TreeViewDemo {
  val blank: Option[String] = None

  def render(in: NodeSeq): NodeSeq = {
    // these are like little templates
    val sampleFile = (".sampleFile ^^" #> "" & // lift template
      ".sampleFile [class]" #> blank)(in) // erase 'sampleFile'
    val sampleFolder = (".sampleFolder ^^" #> "" & // lift template
      ".sampleFolder [class]" #> blank & // erase 'sampleFolder'
      "ul" #> NodeSeq.Empty)(in) // clear out nested 'ul's
    def onclick(name: String) = "href=# [onclick]" #> ("console.log('lift clicked on: " + name + "'); return false;")
    def fileNode(name: String) = {
      (".sampleText" #> name &
        onclick(name))(sampleFile)
    }
    def folderNode(name: String, contents: NodeSeq = NodeSeq.Empty) = {
      var out = (".sampleText" #> name &
        onclick(name))(sampleFolder)
      if (!contents.isEmpty) out = ("* *+" #> <ul>{contents}</ul>)(out)
      out
    }
    val funct = "ul *" #> (
      fileNode("file 1") ++
        folderNode("folder 1 is empty") ++
        folderNode("folder 2",
          fileNode("file 2") ++
            fileNode("file 3")
        )
      )
    funct(in)
  }
}