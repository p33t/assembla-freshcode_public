package code.snippet.experiments

import net.liftweb.util._
import Helpers._
import xml._

object BrowserDetect {
  def ifIe(in: NodeSeq): NodeSeq = {
    Unparsed("<!--[if IE]>\n") ++
      (("* ^*" #> "ignored")(in)) ++ // retain children only
      Unparsed("\n<![endif]-->")
  }
}