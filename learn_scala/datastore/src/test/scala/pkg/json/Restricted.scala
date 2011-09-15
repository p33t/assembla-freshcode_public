package pkg.json


sealed trait Restricted

case class StringChild(str: String) extends Restricted

// TODO: Why is it when this is changed to a 'case object' it can't be used as a type arg?
class VoidChild extends Restricted {
  override def hashCode() = 42

  override def equals(p1: Any) = p1.isInstanceOf[VoidChild]
}
