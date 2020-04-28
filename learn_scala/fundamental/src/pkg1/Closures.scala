package pkg1

object Closures {
  def main(args: Array[String]) {
    var freeVariable = 10

    // a function only has 'bound variables'
    // it is a 'closed term'... meaning the section of code (term) is self contained
    val funct = (x: Int) => x * 10
    println(funct(1))

    // a closure has at least one 'free variable'
    // is is an 'open term' requiring a contextual binding
    val closure = (x: Int) => x * freeVariable
    println(closure(5))
    freeVariable = 9
    println(closure(5))
  }
}