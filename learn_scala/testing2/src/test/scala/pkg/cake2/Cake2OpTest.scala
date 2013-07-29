package pkg.cake2

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import scala.util.Random

@RunWith(classOf[JUnitRunner])
class Cake2OpTest extends FunSuite {
  test("apply()") {
    val str1 = Random.nextString(10)
    val str2 = Random.nextString(10)
    assert(str1 + "_" + str2 ===  Cake2Op(str1)(str2))
  }
}
