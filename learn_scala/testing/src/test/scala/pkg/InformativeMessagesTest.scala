package pkg

import org.scalatest.Suite
import java.lang.AssertionError

/**
 * All these will fail.
 */
class InformativeMessagesTest extends Suite {
  val Actual = "Actual String"
  val Expected = "Expected String"

  def testEquals() {
    assert(Actual == Expected)
  }

  /**No distinction between actual / expected */
  def testTripleEquals() {
    assert(Actual === Expected)
  }

  /**A little more informative than === */
  def testExpect() {
    expect(Expected) {
      Actual
    }
  }

  def testExceptionInterceptionSuccess() {
    intercept[IllegalArgumentException] {
      require(Actual == Expected)
    }
    fail("Just to be consistent")
  }

  def testExceptionInterceptionDidNotThrow() {
    intercept[UnsupportedOperationException] {
      require(Actual == Actual)
    }
  }

  def testExceptionInterceptionWrongException() {
    intercept[UnsupportedOperationException] {
      require(Actual == Expected)
    }
  }
}
