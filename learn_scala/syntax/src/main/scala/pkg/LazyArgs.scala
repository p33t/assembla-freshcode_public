package pkg


object LazyArgs {
  def main(args: Array[String]) {
    var count = 0
    def nextCount() = {
      count = count + 1
      count.toString
    }
    myMethod(nextCount())

    println("Declaring at count " + count)
    val mc = MyClass()(nextCount())
    println("Resulting count is " + count)
    println("Accessing lazy val: " + mc.arg)

  }

  def myMethod(arg: => String) {
    println("1st time: " + arg)
    println("2nd time: " + arg)
  }

  // NOTE: 'call-by-name' not allowed for 'val' args
  case class MyClass()(argSource: => String) {
    lazy val arg = argSource
  }

}