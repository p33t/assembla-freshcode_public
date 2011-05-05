package pkg


object PrefixOperator {

  class Rich(val v: String) {
    def unary_! = {
      new Rich(v.reverse)
    }
  }

  def main(args: Array[String]) {
    val r = new Rich("hello")
    val r2 = !r
    require(r2.v == "olleh")
    println("No error " + this)
  }
}