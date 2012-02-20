package code.snippet.experiments.confidential.diy.authenticated

import xml.NodeSeq
import pkg.CurrentDiyUser

object Unauthenticate {
  def render(ns: NodeSeq): NodeSeq = {
    CurrentDiyUser.logout()
    <p>You have successfully logged out</p>
  }
}