package pkg.cake2

import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import scala.util.Random

@RunWith(classOf[JUnitRunner])
class Cake2TraitTest extends FunSuite with MockFactory {

  test("Method delegated") {
    val subject = new TestSubject
    val input = Random.nextString(10)
    val output = Random.nextString(10)
    (subject.cakeOp.apply _).expects(input).returns(output)
    assert(output === subject.traitOp(input))
  }

  class TestSubject extends Cake2Trait {
    override val cakeOp = mock[String => String]

    def outOfBand = ???
  }
}