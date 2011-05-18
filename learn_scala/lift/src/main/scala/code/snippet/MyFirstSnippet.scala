package code
package snippet

import net.liftweb.http.S
import xml.{Text, NodeSeq}

object MyFirstSnippet {
  def render(n: NodeSeq) = {
    val message = S.attr("message") openOr "<no message>"
    <p>My first snippet output.  Message: '{Text(message)}'</p>
  }
}