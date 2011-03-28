package pkg


object Closures {
  def main(args: Array[String]) {
    var freeVariable = 10

    // a function only has 'bound variables'
    val funct = (x: Int) => x * 10
    println(funct(1))

    // a closure has at least one 'free variable'
    val closure = (x: Int) => x * freeVariable
    println(closure(5))
    freeVariable = 9
    println(closure(5))
  }
}