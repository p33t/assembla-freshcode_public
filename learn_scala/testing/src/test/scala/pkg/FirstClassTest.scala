package pkg

import org.testng.annotations.Test

class FirstClassTest {
  @Test
  def basic() {
    val f = new FirstClass("bruce")
    require(f.s == "bruce")
  }
}