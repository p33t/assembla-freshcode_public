package pkg

object ErrorCheck {
  val One = 1
  val Two = 2

  def main(args: Array[String]) {
    assertCheck()
    requireCheck()
    println(this + " works")
  }

  private def requireCheck() {
    expect(require(One > Two, "Still as expected"), _.isInstanceOf[IllegalArgumentException])
  }

  private def assertCheck() {
    expect(assert(One > Two, "Naturally, one is not greater than two"), _.isInstanceOf[AssertionError])
  }

  private def expect(op: => Unit, allowable: Throwable => Boolean) {
    var didPop = true
    try {
      op
      didPop = false
    }
    catch {
      case e: Throwable => if (!allowable(e)) throw e
    }
    require(didPop, "Expression did not result in an exception.")
  }
}
