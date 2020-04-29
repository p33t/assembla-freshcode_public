package pkg1

/**
 * Implement a prefix operator, which can only one of a limited set of chars.
 */
object PrefixOperator {

  class Rich(val v: String) {
    def unary_! = {
      new Rich(v.reverse)
    }

    def unary_up = {
//      new Rich(v toUpperCase) ... postfix operators must be enabled with a feature flag
      new Rich(v.toUpperCase())
    }
  }

  def main(args: Array[String]) {
    val r = new Rich("hello")
    val r2 = !r
    require(r2.v == "olleh")

    // Does not compile..... only ! ~ + - are allowed as unary prefix
//    val r3 = up r
    val r3 = r.unary_up
    require(r3.v == "HELLO")

    println("No error " + this)
  }
}