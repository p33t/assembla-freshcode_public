package code.snippet

import net.liftweb.http.S


object Referer {
  def render = {
    <span>{S.referer openOr "<no referer>"}</span>
  }
}