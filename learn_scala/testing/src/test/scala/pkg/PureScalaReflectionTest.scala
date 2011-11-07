package pkg

import org.scalatest.Suite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PureScalaReflectionTest extends Suite {

  def testByReflection() {
    println("testByReflection")
  }
}