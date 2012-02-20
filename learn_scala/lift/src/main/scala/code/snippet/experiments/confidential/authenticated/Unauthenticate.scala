package code.snippet.experiments.confidential.authenticated

import xml.NodeSeq
import pkg.CurrentUser

object Unauthenticate {
  def render(ns: NodeSeq): NodeSeq = {
    CurrentUser.logout()
    <p>You have successfully logged out</p>
  }
}