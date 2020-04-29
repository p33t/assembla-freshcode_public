package pkg1

object Identifiers {
  val SomeConst = "Constants are not UPPER_CASE.  They are UpperCamelCase by convention"

  def `yield`() {
    println("yield was called. It needs back ticks because its a reserved word but might be useful when calling Java's Thread.yield (?)")
  }

  def main(args: Array[String]) {
    `yield`()
  }
}