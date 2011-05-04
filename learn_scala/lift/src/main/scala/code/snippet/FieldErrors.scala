package code.snippet

import xml.NodeSeq
import net.liftweb.http.S


object FieldErrors {
  def render = {

    S.notice("General Notice")
    S.warning("General Warning")
    S.error("General Error")

    S.notice("myField", "The Notice")
    S.warning("myField", "The Warning")
    S.error("myField", "The Error")

    // a pass through... the render method can return the desired NodeDeq transformer function
    (n: NodeSeq) => n
  }
}
