package code.snippet

import xml.NodeSeq
import net.liftweb._
import http.js._
import http.S
import JE._
import widgets.tree.TreeView


object TreeViewWidgetDemo {
  def standard(in: NodeSeq): NodeSeq = {
    val ul_id = S.attr("ul_id") openOr error("The ul_id attribute is required.")
    // See http://docs.jquery.com/Plugins/Treeview/treeview#options
    TreeView(ul_id, JsObj(("animated" -> "slow"), ("collapsed" -> true))) ++ in
  }
}
