package pkg.bdd

import org.scalatest.FunSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BddTest extends FunSpec {
  describe("Test BDD") {
    it("can give details about what it is doing") {
      assert(1 == 1)
    }
  }
}