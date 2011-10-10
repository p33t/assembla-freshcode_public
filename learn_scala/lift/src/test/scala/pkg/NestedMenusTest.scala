package pkg

import org.testng.annotations.Test
import collection.mutable.ListBuffer
import org.scalatest.Suite


@Test
class NestedMenusTest extends Suite {

  import NestedMenusTest._

  def testNode() {
    val root = new StringNode
    expect(Nil)(root.path)
    def check(expected: List[String]) {
      expect(expected) {
        root.walkChildren(n => if (n.isRoot) null else n.name)
      }
    }
    root.ensurePath(List("one", "two", "three"))
    check(List("one", "two", "three"))
    expect(List("one", "twotwo")) {
      root.ensurePath(List("one", "twotwo")).path 
    }
    check(List("one", "two", "three", "twotwo"))
  }

  def testProcessSparsePaths() {
    // want to convert a list of paths into a tree that has a target for each node
    // When sorted, all siblings should be adjacent.
    val fancyData = List("0/0/0", "0/1/0", "0/1/1", "1/0", "2", "2/0")
    val expected = List(
      ("0", "0/0/0"),
      ("0/0", "0/0/0"),
      ("0/0/0", "0/0/0"),

      ("0/1", "0/1/0"),
      ("0/1/0", "0/1/0"),

      ("0/1/1", "0/1/1"),

      ("1", "1/0"),
      ("1/0", "1/0"),

      ("2", "2"),

      ("2/0", "2/0")
    )
    expect(expected) {
      val paths = fancyData.map(_.split("/").toList)
      val root = new StringNode
      paths.map(root.ensurePath(_))

      def altPath(n: StringNode): List[String] = {
        val path = n.path
        if (paths.contains(path)) path
        else {
          require(!n.children.isEmpty, n.path + " is empty")
          altPath(n.children(0))
        }
      }

      root.walkChildren {
        n =>
          (n.path.mkString("/"), altPath(n).mkString("/"))
      }
    }
  }
}

object NestedMenusTest {

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
        val c = new StringNode(Some((head, this)))
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

}
