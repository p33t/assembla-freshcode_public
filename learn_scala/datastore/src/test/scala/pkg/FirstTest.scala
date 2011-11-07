package pkg

import org.scalatest.Assertions
import org.junit.Test

class FirstTest {

  @Test
  def smoke() {
    Assertions.expect(true)(true)
  }
}