package code.snippet

import xml.NodeSeq
import net.liftweb._
import http.js._
import http.js.JsCmds.Alert
import JE._
import widgets.tree.TreeView


object TreeViewDemo {
  def render(in: NodeSeq): NodeSeq = {
    // See http://docs.jquery.com/Plugins/Treeview/treeview#options
    TreeView("tree-contents", JsObj(("animated" -> "slow"), ("collapsed" -> true))) ++ in
  }
}
