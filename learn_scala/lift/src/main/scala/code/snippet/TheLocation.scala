package code
package snippet

import xml.NodeSeq
import net.liftweb._
import util._
import Helpers._

import common._
import http._

object TheLocation {
  def render(in: NodeSeq): NodeSeq = {
    val location = S.location match {
      case Full(l) => l.name
      case _ => "Unknown Location"
    }
    <span>{location}</span>
  }
}
