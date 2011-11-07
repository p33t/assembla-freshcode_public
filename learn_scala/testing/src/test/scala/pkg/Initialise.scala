package pkg

import org.scalatest.Assertions
import org.junit.{Before, Test}

class Initialise extends Assertions {
  var s = "default"

  @Before
  def init() {
    s = "initialised"
  }

  @Test
  def test1() {
    expect("initialised")(s)
  }
}
