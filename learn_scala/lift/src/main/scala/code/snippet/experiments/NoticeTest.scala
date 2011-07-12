package code.snippet.experiments

import xml.NodeSeq
import net.liftweb.http.S

object NoticeTest {
  def render(in: NodeSeq) = {
    S.notice("This is a test notice!")
    NodeSeq.Empty
  }
}