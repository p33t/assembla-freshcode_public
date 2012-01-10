package pkg.json.versions

import net.liftweb.json._

/**
 * A special serializer with the ability to deserialize old versions.
 */
object MyModelSerializer extends Serializer[MyModel] {
  private val CurrentVersionField = JField("version", JInt(2))
  private val DefaultVersion = 1 // version numbering introduced AFTER this version

//  override def serializer:   PartialFunction[(String, Any), Option[(String, Any)]] = Map(),
//  override def deserializer: PartialFunction[JField, JField] = Map()

  def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), MyModel] = {
    case (ti, obj: JObject) if (ti.clazz == classOf[MyModel]) => convertToCurrent(obj)
  }

  def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
    Map.empty
//    weave in a version number
//    case mm: MyModel => {
    // THis causes StackOverflow
//      val obj = Extraction.decompose(mm).asInstanceOf[JObject]
//      JObject(CurrentVersionField :: obj.obj)
//    }
  }

  private def convertToCurrent(obj: JObject)(implicit formats: Formats): MyModel = {
    obj match {
      case JObject(CurrentVersionField :: _) => Extraction.extract[MyModel](obj)
      // put extra version numbers in here
      case _ =>
        // handling for default version number
        val mm1 = Extraction.extract[MyModel1](obj)
        val elems = mm1.elems.map(e1 => (e1.code, Elem(e1.num))).toMap
        MyModel(elems)
    }
  }
}