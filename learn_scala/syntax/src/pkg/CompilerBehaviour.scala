package pkg


object CompilerBehaviour {

  class MyVar[T]{
    var t: T = _
    def apply() = t
    def update(t:T):Unit = this.t = t
  }

  def main(args: Array[String]) {
    val myVar = new MyVar[String]
    println(myVar()) // <<<<<<<< Automatic use .apply()
    myVar() = "updated!" // <<<<<<<<< Automatic use .update()
    println(myVar())

    myVar update "no parens needed" // <<<< no need for parens
    println(myVar())
  }
}