package pkg


object FunctionLiterals2 {
  def main(args: Array[String]) {
    def myEndsWith(query:String) = (_:String).endsWith(query)
    println(myEndsWith(".doc")("minimal.doc"))

    def twice(op: Double => Double, x: Double) = op(op(x))
    println(" Want 13: " + twice(_ + 5, 3))

    singleParamCurlyBraces
    byNameParameter
  }

  def singleParamCurlyBraces: Unit = {
    def curlyBraces(s: AnyRef) = println("{" + s + "}")
    curlyBraces {
      "Can substitute curly braces if one arg."
    }

    def add(x: Int)(y: Int) = x + y
    def increment(y: Int) = add(1)(y)
    println {
      increment {
        10
      }
    }
  }

  def byNameParameter: Unit = {
    var enabled = false
    def myAssert(expr: => Boolean) = if (enabled && !expr) throw new AssertionError
    myAssert(10 / 0 == 1)
    println("no error!")
  }
}