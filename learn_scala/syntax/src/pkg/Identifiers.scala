package pkg


object Identifiers {
  val SomeConst = "Constants are not UPPER_CASE.  They are UpperCamelCase by convention"

  def `yeild` {
    println("yeild was called. It needs back ticks because its a reserved word but might be useful when calling Java's Thread.yeild (?)")
  }

  def main(args: Array[String]) {
    `yeild`
  }
}