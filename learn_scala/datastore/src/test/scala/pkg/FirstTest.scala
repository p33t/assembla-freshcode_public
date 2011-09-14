package pkg

import org.testng.annotations.Test
import org.testng.Assert


@Test
class FirstTest {

  def smoke() {
    Assert.assertTrue(true)
  }
}