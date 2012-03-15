package pkg

import org.scalatest.Suite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class UsesJavaTest extends Suite {
  def test() {
    expect(true) {
      UsesJava.isValidName("bruce")
    }
    expect(false) {
      UsesJava.isValidName("xxx")
    }
  }
}