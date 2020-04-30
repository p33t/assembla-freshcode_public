package pkg1

/**
 * Demonstrate how 'require()' is used / handled
 */
object RequireCatch {
  def main(args: Array[String]) {
    requireEg()
  }

  def requireEg() {
    require (!"some boolean expression".isEmpty)

    try {
      require("otherwise an IllegalArgExcept is thrown".isEmpty)
      assert(false, "Should not get here")
    }
    catch {
      case e: IllegalArgumentException => // nothing
    }
  }
}