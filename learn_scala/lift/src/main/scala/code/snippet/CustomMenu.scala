package code.snippet

import net.liftweb.widgets.menu.MenuWidget
import xml.{Text, NodeSeq}

object CustomMenu {

  private def styleElem = {
    <style type="text/css">
      {Text(
      ".float-center-item {\n" +
        "  left: 50%;\n" +
        "  position: relative;\n" +
        "}\n\n" +
        ".float-center-wrapper {\n" +
        "  float: right;\n" +
        "  position: relative;\n" +
        "  left: -50%;\n" +
        "}\n\n" +
        ".float-clear {\n" +
        "  clear:both;\n" +
        "}\n" +
        "ul.sf-menu {margin-bottom: 0}"
    )}
    </style>
  }

  def render(in: NodeSeq) = {
    // Need to get MenuWidget to provide the plumbing
    // We render menu ourselves because MenuWidget doesn't seem to do it properly.
    MenuWidget(List("No Group")) ++
      <head>
        {styleElem}
      </head> ++
      <div class="float-center-wrapper">
          <lift:Menu.builder top:class="sf-menu float-center-item" linkToSelf="true" expandAll="true"/>
      </div> ++
      // This div terminates the floating effects properly.
      <div class="float-clear"></div>
  }
}
