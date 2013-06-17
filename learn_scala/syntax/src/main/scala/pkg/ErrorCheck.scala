package pkg

/**
 * Remember that Assertions can be enabled / disabled using JVM flags.
 * See also http://subversion.assembla.com/svn/freshcode_public/learn_scala/testing
 */
object ErrorCheck {
  val One = 1
  val Two = 2

  def main(args: Array[String]) {
    assertCheck()
    requireCheck()
    ensureCheck()
    println(this + " works")
  }

  private def ensureCheck() {
    expect[AssertionError](One.ensuring(_ > Two, "And still it continues"))
  }

  private def requireCheck() {
    expect[IllegalArgumentException](require(One > Two, "Still as expected"))
  }

  private def assertCheck() {
    // the least information.  Merely a false condition.
    expect[AssertionError](assert(One > Two, "Naturally, one is not greater than two"))
  }

  private def expect[T](op: => Unit) {
    var didPop = true
    try {
      op
      didPop = false
    }
    catch {
      case e: Throwable => {
        if (!e.isInstanceOf[T]) throw e
        println("Successfully caught...\n" + e)
      }
    }
    require(didPop, "Expression did not result in an exception.")
  }
}
