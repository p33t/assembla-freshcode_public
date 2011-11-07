package pkg

import org.junit.Test

class FirstClassTest {
  @Test
  def basic() {
    val f = new FirstClass("bruce")
    require(f.s == "bruce")
  }
}