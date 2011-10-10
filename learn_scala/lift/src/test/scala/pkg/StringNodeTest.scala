package pkg

import org.testng.annotations.Test
import org.scalatest.Suite
import bootstrap.liftweb.StringNode

@Test
class StringNodeTest extends Suite {

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
