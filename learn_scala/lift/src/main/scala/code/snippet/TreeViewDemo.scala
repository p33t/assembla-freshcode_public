package code.snippet

import net.liftweb.util._
import Helpers._
import xml._

object TreeViewDemo {
  def render(in: NodeSeq): NodeSeq = {
    val sampleFile = (".sampleFile ^^" #> "")(in)
    val sampleFolder = (".sampleFolder ^^" #> "" & "ul" #> NodeSeq.Empty)(in) // clear out nested 'ul's
    // TODO: Change this to the real thing.  It only puts in one file an one folder.
    val funct = "ul *" #> (sampleFolder ++ sampleFile)
    funct(in)
  }
}