package code.snippet

import net.liftweb.widgets.menu.MenuWidget
import xml.{Text, NodeSeq}


object CustomMenu {
  private val styleBody = "/* Customisations to get menu working smoothly */\n" +
    ".sf-menu span {\n" +
    "  display: block;\n" +
    "  position: relative;\n" +
    "  border-left: 1px solid #fff;\n" +
    "  border-top: 1px solid #CFDEFF;\n" +
    "  padding: .75em 1em;\n" +
    "  text-decoration: none;\n" +
    "}\n" +
    "ul.sf-menu {\n" +
    "  left: 50%;\n" +
    "  position: relative;\n" +
    "}\n" +
    ".menu-wrapper { /* Causes center floating-ish*/ \n" +
    "  float: right;\n" +
    "  position: relative;\n" +
    "  left: -50%;\n" +
    "  text-align: left;\n" +
    "  margin-bottom: 2em;\n" +
    "}\n" +
    ".menu-chaser { /* clears out the floating effects */\n" +
    "  height:1px;\n" +
    "  overflow:hidden;\n" +
    "  margin-top:-1px;\n" +
    "  clear:both;\n" +
    "}"

  private def style = {
    <style type="text/css">
      {Text(styleBody)}
    </style>
  }

  def render(in: NodeSeq) = {
    style ++ <div class="menu-wrapper">
      {MenuWidget()}
    </div> ++ <div class="menu-chaser"></div>
  }
}
