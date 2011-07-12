package code.snippet.experiments

import xml.NodeSeq
import net.liftweb.http.S


object RedirectNow {
  def render(in: NodeSeq) = {
    S.notice("You've been redirected.")
    S.redirectTo("/experiments/embed.html")
    NodeSeq.Empty
  }
}