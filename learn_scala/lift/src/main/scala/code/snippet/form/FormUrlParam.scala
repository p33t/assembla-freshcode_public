package code.snippet.form

import xml.NodeSeq
import net.liftweb.http.S
import net.liftweb.common.Full


object FormUrlParam {
  def render = {
    S.param("message") match {
      case Full(message) => S.notice("Message was: " + message)
      case _ => // nothing
    }

    (in: NodeSeq) => in
  }
}