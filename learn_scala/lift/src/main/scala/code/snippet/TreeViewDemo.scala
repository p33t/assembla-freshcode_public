package code.snippet

import net.liftweb.util._
import Helpers._
import xml._

object TreeViewDemo {

  def render(in: NodeSeq): NodeSeq = {
    val sampleFile = (".sampleFile ^^" #> "")(in)
    val sampleFolder = (".sampleFolder ^^" #> "" & "ul" #> NodeSeq.Empty)(in) // clear out nested 'ul's
    def fileNode(name: String) = {
      (".sampleText" #> name)(sampleFile)
    }
    // TODO: Add folders and nesting.
    def folderNode(name: String, contents: NodeSeq) {
      (".sampleText" #> name & "* *+" #> contents)(sampleFolder)
    }
    val funct = "ul *" #> (
      fileNode("file 1")
      )
    funct(in)
  }
}