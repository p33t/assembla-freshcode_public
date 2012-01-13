package pkg.json

import net.liftweb.json._

object RestrictedSerializer extends Serializer[Restricted] {
  private val StrRep = "VoidChild"

  def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), Restricted] = {
    // NOTE: For generified types the ti.parameterizedType must be examined too.
    case (ti, jval) if (ti.clazz == classOf[Restricted] && jval == JString(StrRep)) => new VoidChild
    case (ti, jval) if (ti.clazz == classOf[Restricted]) => jval.extract[StringChild]
  }

  def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
    case vc: VoidChild => Extraction.decompose(StrRep)(format)
  }
}
