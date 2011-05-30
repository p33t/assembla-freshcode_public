package pkg

import org.testng.annotations.{Test, BeforeTest}
import org.scalatest.Assertions
import org.testng.Assert._

class Initialise extends Assertions {
  var s = "default"

  @BeforeTest
  def init() {
    s = "initialised"
  }

  @Test
  def test1() {
    assertEquals("initialised", s)
  }
}
