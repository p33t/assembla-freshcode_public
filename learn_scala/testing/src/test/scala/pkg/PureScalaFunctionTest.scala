package pkg

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PureScalaFunctionTest extends FunSuite {
  test("Some test description") {
    // the function being supplied
    println("test in a function")
  }
}
