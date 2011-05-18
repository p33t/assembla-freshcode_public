package code.snippet

import xml.{Text, NodeSeq}

object CssSelectDemo {
  def echo(in: NodeSeq): NodeSeq = {
    in ++ <pre>{Text(in.toString())}</pre>
  }
}