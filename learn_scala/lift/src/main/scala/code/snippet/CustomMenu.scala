package code.snippet

import net.liftweb.widgets.menu.MenuWidget
import xml.{Text, NodeSeq}

object CustomMenu {

  private def styleElem = {
    <style type="text/css">
      {Text(
      """
.float-center-item {
  left: 50%;
  position: relative;
}

.float-center-wrapper {
  float: right;
  position: relative;
  left: -50%;
}

.float-clear {
  clear:both;
}
/* Margin fix for FireFox */
ul.sf-menu {
  margin-bottom: 0
}
.sf-menu a, .sf-menu a:visited  {
    color: #13a;
}
.sf-menu li, .sf-menu li li, .sf-menu li li li {
    background: rgb(252, 179, 22);
}
.sf-menu li:hover, .sf-menu li.sfHover,
.sf-menu a:focus, .sf-menu a:hover, .sf-menu a:active {
    background: rgb(255, 217, 79);
    outline: 0;
}
      """
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
