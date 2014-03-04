package pkg.mock

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import org.scalamock.scalatest.MockFactory

@RunWith(classOf[JUnitRunner])
class MockTest extends FunSuite with MockFactory {
  test("Basic mocking (expectation then exercise)") {
    val m = mock[SomeTrait]
    (m.traitMethod _).expects("arg")
    m.traitMethod("arg")
  }

  test("Basic stubbing (record then verify)") {
    val s = stub[SomeTrait]
    s.traitMethod("arg")
    (s.traitMethod _).verify("arg")
  }

  test("Mocking a method") {
    val mockProcStr = mock[String => String]
    val subject = new ComplexTrait {
      val procStr = mockProcStr
    }

    (mockProcStr.apply _).expects("bruce").returns("lee")

    assert(subject("bruce") === "lee")
  }
}