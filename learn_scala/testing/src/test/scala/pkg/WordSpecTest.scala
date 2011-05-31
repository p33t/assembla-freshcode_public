package pkg

import org.scalatest.WordSpec
import org.scalatest.matchers.MustMatchers

class WordSpecTest extends WordSpec with MustMatchers {
  "A file path" when {
    "has multiple elements" must {
      "have a / separating the elements" in {}
      "cannot have 2 / in a row" in {}
    }
    "has only one element" must {
      "not have any / chars" in {}
      "cannot be blank" in {}
    }
  }
}
