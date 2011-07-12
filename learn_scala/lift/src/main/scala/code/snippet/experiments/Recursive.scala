package code.snippet.experiments

import xml.NodeSeq


object Recursive {
  val Finished = "Finished"

  def render(in: NodeSeq) = {
    val newText = in.text.trim match {
      case "3" => "2"
      case "2" => "1"
      case "1" => "0"
      case "0" => Finished
    }
    // TODO: Figure out how to construct line-break sensitive XML.  Intellij formatting inserts them automatically.
    if (newText != Finished)
      <span>
        &lt; <span class="lift:Recursive">
        {newText}
      </span> &gt;
      </span>
    else {
      <span>
        {newText}
      </span>
    }
  }
}