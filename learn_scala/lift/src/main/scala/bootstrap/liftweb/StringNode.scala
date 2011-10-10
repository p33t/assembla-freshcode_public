package bootstrap.liftweb

import collection.mutable.ListBuffer

class StringNode(nameParent: Option[(String, StringNode)] = None) {
  val nameOpt: Option[String] = if (nameParent.isDefined) Some(nameParent.get._1) else None
  val parentOpt: Option[StringNode] = if (nameParent.isDefined) Some(nameParent.get._2) else None
  val isRoot = parentOpt.isEmpty
  val children = new ListBuffer[StringNode]

  def name = nameOpt.get

  def parent = parentOpt.get

  def path = pathRev.reverse

  private def pathRev: List[String] = {
    if (isRoot) Nil
    else name :: parent.pathRev
  }

  def ensurePath(desiredPath: List[String]): StringNode = {
    if (desiredPath.isEmpty) return this
    val head = desiredPath.head
    val childOpt = children.find(_.name == head)
    val child = childOpt.getOrElse {
      val c = StringNode(head, this)
      children += c
      c
    }
    child.ensurePath(desiredPath.tail)
  }

  def walk[T](fn: StringNode => T): List[T] = {
    fn(this) :: children.toList.flatMap(_.walk(fn))
  }

  def walkChildren[T](fn: StringNode => T): List[T] = {
    children.toList.flatMap(_.walk(fn))
  }
}

object StringNode {
  def apply() = {
    new StringNode(None)
  }

  def apply(name: String, parent: StringNode) = {
    new StringNode(Some(name, parent))
  }
}
