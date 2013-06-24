package pkg.basic

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import scala.collection.mutable

@RunWith(classOf[JUnitRunner])
class BasicTest extends FunSuite {

  test("test infrastructure working") {
    val stack = new mutable.Stack[Int]

    val thrown = intercept[NoSuchElementException] {
      stack.pop()
    }
    assert(thrown.getMessage === "head of empty list")

    stack.push(3)
    assert(stack.pop === 3)
  }
}