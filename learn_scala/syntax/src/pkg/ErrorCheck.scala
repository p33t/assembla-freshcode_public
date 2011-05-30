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
    expect[IllegalArgumentException](require(One > Two, "Still as expected"))
  }

  private def assertCheck() {
    expect[AssertionError](assert(One > Two, "Naturally, one is not greater than two"))
  }

  private def expect[T](op: => Unit) {
    var didPop = true
    try {
      op
      didPop = false
    }
    catch {
      case e: Throwable => if (!e.isInstanceOf[T]) throw e
    }
    require(didPop, "Expression did not result in an exception.")
  }
}
