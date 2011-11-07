package pkg

import org.scalatest.prop.Checkers
import org.scalacheck.Prop._
import org.scalatest.WordSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

// This doesn't work with FlatSpec
@RunWith(classOf[JUnitRunner])
class SpecCheckTest extends WordSpec with Checkers {
  def greaterThan(ref: Int)(arg: Int): Boolean = ref > arg

  "The greaterThan function" must {
    "respond true" in {
      check((h: Int) => (0 > h) ==> greaterThan(0)(h))
    }
  }
}