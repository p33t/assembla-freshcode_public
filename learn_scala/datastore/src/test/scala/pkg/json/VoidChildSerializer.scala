package pkg.json

import net.liftweb.json._

object VoidChildSerializer extends Serializer[VoidChild] {
  private val StrRep = "VoidChild"

  def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), VoidChild] = {
    case (ti, jval) if (ti.clazz == classOf[Restricted] && jval == JString(StrRep)) => new VoidChild
  }

  def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
    case vc: VoidChild => Extraction.decompose(StrRep)(format)
  }
}
