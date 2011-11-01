package pkg

import org.scalatest.Suite
import com.borachio.scalatest.MockFactory
import com.borachio.ExpectationException

class MockTest extends Suite with MockFactory {
  // This test code is from borachio.com
  def testFoldLeft() {
    val f = mockFunction[String, Int, String]

    f expects("initial", 0) returning "intermediate one"
    f expects("intermediate one", 1) returning "intermediate two"
    f expects("intermediate two", 2) returning "intermediate three"
    f expects("intermediate three", 3) returning "final"

    expect("final") {Seq(0, 1, 2, 3).foldLeft("initial")(f)}
  }

  def testUnsatisfiedExpectations() {
    autoVerify = false
    val f = mockFunction[String, String]
    f expects("bruce") returning "bruce"
//    expect("bruce"){f("bruce")}
    intercept[ExpectationException]{
      verifyExpectations()
    }
  }
}
