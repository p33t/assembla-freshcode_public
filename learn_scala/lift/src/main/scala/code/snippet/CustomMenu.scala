package code.snippet

import net.liftweb.widgets.menu.MenuWidget
import xml.NodeSeq


object CustomMenu {
  def render(in: NodeSeq) = {
    // Need to get MenuWidget to provide the plumbing
    // We render menu ourselves because MenuWidget doesn't seem to do it properly.
    MenuWidget(List("No Group")) ++
        <lift:Menu.builder top:class="sf-menu" linkToSelf="true" expandAll="true"/> ++
    // This div terminates the floating effects properly.
      <div style="height:1px; overflow:hidden; margin-top:-1px; clear:both;"></div>
  }
}
