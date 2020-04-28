package pkg2

import scala.xml.Elem

/**
 * Basic XML manipulation
 */
object XmlModify {
  val before = <root attr1="before" attr2="keep">
    <child>hello</child>
  </root>
  val expAfter = <root attr1="after" attr2="keep">
    <child>hello</child>
  </root>
  val expWorld = <root attr1="before" attr2="keep">
    <child whom="world">hello</child>
  </root>

  def main(args: Array[String]) {
    // Change an attribute
    val after = before.copy(attributes = before.attributes.append(<alt attr1="after"/>.attributes))
    assert(after == expAfter)

    // Add an attribute to a child element
    val world = before.copy(
      child = before.child.map {
        case target: Elem if target.label == "child" && target.attributes.get("whom").isEmpty =>
          val alt = <x whom="world"/>
          target.copy(attributes = target.attributes.append(alt.attributes))
        case other => other
      }
    )
    assert(world == expWorld)
  }
}