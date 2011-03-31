package pkg

import util.Random


object FunctionLiteral {
  def main(args: Array[String]) {
    val myFunct = (i: Int) => "Function Literal: " + i

    println(myFunct(22))

    multiline
    targetTyping
    placeHolderSyntax
    partialApplication
    parameterLists
    parameterListsAlt
  }

  private def multiline() {
    // Hmmm this appears to be only way to define explicit return type
    val f:() => String = () => {
      if (Random.nextBoolean()) "TRUE"
      else "FALSE"
    }

    println(f())
    println(f())
    println(f())
  }

  private def targetTyping() {
    var intToString: Int => String = null
    // type of args is inferred and no need for redundant parens
    intToString = x => "Minimal Syntax " + x
    println(intToString(23))
  }

  private def placeHolderSyntax {
    var intToString: Int => String = null
    // can skip the parameter declaration and use an implicit '_'
    intToString = "Place holder syntax " + _
    println(intToString(98))

    var intx3ToInt: (Int, Int, Int) => Int = null
    // can apply to multiple args but each can only be used once and types are probably needed
    intx3ToInt = _ + _ + _
    println(intx3ToInt(2, 7, 9))
  }

  private def partialApplication {
    def myFunct(i: Int): String = {
      "A nested function " + i
    }
    println(myFunct(10))
    // need the place holder to convert a function into a function value, which can then be passed around
    val myFunctionValue = myFunct _
    myFunctionValue(20)
    val refToValue = myFunctionValue
    refToValue(30)

    def intIntToString(i: Int, j: Int): String = {
      "Sum of " + i + " and " + j + " is " + (i + j)
    }
    println(intIntToString(2, 2))

    // only need 1 place holder (with no parens) to signify entire arg list
    val argListed = intIntToString _

    // is this that fabled currying?
    val partial = argListed((_: Int), 1)
    println(partial(9))
  }

  private def parameterLists() {
    def funct(i: Int)(j: Int) {
      println(i + " + " + j + " = " + (i + j))
    }
    funct(1)(2)

    val f2 = funct(1)(_)
    f2(3)
    // Hmm.. this needs a type(?!)
    val f3 = funct(_: Int)(2)
    f3(0)
    val f4 = funct(_)
    f4(4)(3)
    // combining param lists?
    val f5 = funct(_: Int)(_: Int)
    f5(7, 8)
    val f6 = funct _
    f6(9)(10)
  }

  private def parameterListsAlt() {
    def funct(i: Int, j: Int) {
      println(i + " + " + j + " = " + (i + j))
    }

    // splitting parameter lists?
    def f2(i: Int)(j: Int) = funct(i, j)
    f2(1)(3)
  }
}