package pkg


object RequireCatch {
  def main(args: Array[String]) {
    requireEg
  }

  def requireEg {
    require (!"some boolean expression".isEmpty)

    try {
      require("otherwise an IllegalArgExcept is thrown".isEmpty)
    }
    catch {
      case e: IllegalArgumentException => // nothing
    }
  }
}