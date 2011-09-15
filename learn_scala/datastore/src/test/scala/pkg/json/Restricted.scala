package pkg.json


sealed trait Restricted

case class StringChild(str: String) extends Restricted

class VoidChild extends Restricted {
  override def hashCode() = 42

  override def equals(p1: Any) = p1.isInstanceOf[VoidChild]
}
