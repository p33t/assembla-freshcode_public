package code.snippet.experiments

import net.liftweb.http.S
import xml.{Text, NodeSeq}

object FancyMenus {
  val FancyParam = "fancyParam"

  def urlParamMsg(in: NodeSeq): NodeSeq = {
    val fancyParam = S.param(FancyParam)
    Text {"Parameter is: " + fancyParam.openOr("[None]")}
  }
}
