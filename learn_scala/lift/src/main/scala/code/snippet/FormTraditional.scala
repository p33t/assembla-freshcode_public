package code.snippet

import xml.NodeSeq
import net.liftweb.http.S

object FormTraditional {
  def render(in: NodeSeq): NodeSeq = {
    if (S.post_?) {
      val box = S.param("message")
      box.foreach{s: String => S.notice("Message is " + s)}
      S.redirectTo("/forms.html")
      NodeSeq.Empty
    }
    else in
  }
}
