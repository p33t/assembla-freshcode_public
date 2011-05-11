package code.snippet

import net.liftweb.widgets.menu.MenuWidget
import xml.{Text, NodeSeq}

object CustomMenu {

  private def styleElem = {
    <style type="text/css">
      {Text("/*float center effect*/\n" +
      ".menu-wrapper{float: right; position: relative; left: -50%; text-align: left; margin-bottom: 2em;}\n" +
      "ul.sf-menu{left: 50%; position: relative;}")}
    </style>
  }

  def render(in: NodeSeq) = {
    // Need to get MenuWidget to provide the plumbing
    // We render menu ourselves because MenuWidget doesn't seem to do it properly.
    MenuWidget(List("No Group")) ++ styleElem ++
      <div class="menu-wrapper">
          <lift:Menu.builder top:class="sf-menu" linkToSelf="true" expandAll="true"/>
      </div> ++
      // This div terminates the floating effects properly.
      <div style="height:1px; overflow:hidden; margin-top:-1px; clear:both;"></div>
  }
}
