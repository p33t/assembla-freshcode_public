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
    def folderNode(name: String, contents: NodeSeq = NodeSeq.Empty) = {
      var out = (".sampleText" #> name)(sampleFolder)
      if (!contents.isEmpty) out = ("* *+" #> <ul>
        {contents}
      </ul>)(out)
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