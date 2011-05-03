package code.snippet

import xml.NodeSeq
import pkg.UrlRemainder
import net.liftweb.common.Full

class WildProcessing(ur: UrlRemainder) {
  def render(in: NodeSeq): NodeSeq = {
    <span>Remainder =
      {ur.remainder}
    </span>
  }
}

object WildProcessing {
  val parser = (s: String) => Full(UrlRemainder(s))
  val encoder = (ur: UrlRemainder) => ur.remainder
}