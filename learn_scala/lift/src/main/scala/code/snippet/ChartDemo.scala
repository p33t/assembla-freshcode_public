package code.snippet

import net.liftweb._
import http.js.jquery.JqWiringSupport
import http.js.{JsCmd, JsCmds}
import JsCmds._
import http.{WiringUI, SHtml}
import util._
import Helpers._
import java.util.Date
import java.lang.Thread
import xml.{Node, Elem, Text, NodeSeq}

object ChartDemo {
  def defaultData(in: NodeSeq): NodeSeq = {
    <script type="text/javascript">
      dataD = [[0.0, 3.0], [0.25, 3.0], [0.5, 1.0], [0.75, 3.0], [1.0, 3.0]];
    </script>
  }
}
