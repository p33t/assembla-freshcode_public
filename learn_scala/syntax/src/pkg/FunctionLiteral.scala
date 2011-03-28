package pkg


object FunctionLiteral {
  def main(args: Array[String]) {
    val myFunct = (i: Int) => "Function Literal: " + i

    println(myFunct(22))

    targetTyping
    placeHolderSyntax
    partialApplication
  }

  private def targetTyping: Unit = {
    var intToString: Int => String = null
    // type of args is inferred and no need for redundant parens
    intToString = x => "Minimal Syntax " + x
    println(intToString(23))
  }

  private def placeHolderSyntax: Unit = {
    var intToString: Int => String = null
    // can skip the parameter declaration and use an implicit '_'
    intToString = "Place holder syntax " + _
    println(intToString(98))

    var intx3ToInt: (Int, Int, Int) => Int = null
    // can apply to multiple args but each can only be used once and types are probably needed
    intx3ToInt = _ + _ + _
    println(intx3ToInt(2, 7, 9))
  }

  private def partialApplication: Unit = {
    def myFunct(i: Int): String = {
      "A nested function " + i
    }
    println(myFunct(10))
    // need the place holder to convert a function into a function value, which can then be passed around
    val myFunctionValue = myFunct _
    myFunctionValue(20)
  }
}