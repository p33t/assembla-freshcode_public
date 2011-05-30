package pkg

import org.scalatest.prop.Checkers
import org.scalacheck.Prop._
import org.scalatest.WordSpec

// This doesn't work with FlatSpec
class SpecCheckTest extends WordSpec with Checkers {
  def greaterThan(ref: Int)(arg: Int): Boolean = ref > arg

  "The greaterThan function" must {
    "respond true" in {
      check((h: Int) => (0 > h) ==> greaterThan(0)(h))
    }
  }
}