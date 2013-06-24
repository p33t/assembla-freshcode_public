package pkg.basic

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import scala.collection.mutable

@RunWith(classOf[JUnitRunner])
class BasicTest extends FunSuite {

  test("test infrastructure working") {
    val stack = new mutable.Stack[Int]

    // Exception interception
    val thrown = intercept[NoSuchElementException] {
      stack.pop()
    }
    assert(thrown.getMessage === "head of empty list")

    // simple assertion
    // NOTE: the '===' function returns an Option[String]
    stack.push(3)
    assert(stack.pop === 3)
  }
}