package pkg.cake

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

@RunWith(classOf[JUnitRunner])
class CakeTraitTest extends FunSuite {
  test("Is -1 when same as 'variable'") {
    def check(arg: String) {
      checkTraitMethod(arg, arg, -1)
    }
    check("cats")
    check("dogs")
    check("bruce")
  }

  test("Is 'length' when not same as variable") {
    def check(arg: String) {
      checkTraitMethod("xyz", arg, arg.length)
    }
    check("cats")
    check("dogs")
    check("bruce")
  }


  private def checkTraitMethod(variable: String, arg: String, expected: Int) {
    val actual = new Impl(variable).traitMethod(arg)
    assert(actual === expected)
  }

  /**
   * Test implementation of CakeTrait
   */
  class Impl(value: String) extends CakeTrait with CakeTraitSupport {
    def variable = value

    def variable_=(s: String) {
      throw new UnsupportedOperationException("Cannot set 'variable'")
    }
  }
}
