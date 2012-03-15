package pkg

import xml.Text
import org.scalatest.Suite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class EscapeHtmlTest extends Suite {
  def testEscape() {
    expect("&amp;") {
      Text("&").toString()
    }
  }
}