package pkg


object Functions {
  def main(args: Array[String]) {
    val myFunct = (i: Int) => "Function Literal: " + i

    println(myFunct(22))

    targetedTypingOfFunctionLiteral
  }

  private def targetedTypingOfFunctionLiteral: Unit = {
    var intToString: Int => String = null
    // type of args is inferred and no need for redundant parens
    intToString = x => "Minimal Syntax " + x
    println(intToString(23))
  }
}