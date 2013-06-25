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
}