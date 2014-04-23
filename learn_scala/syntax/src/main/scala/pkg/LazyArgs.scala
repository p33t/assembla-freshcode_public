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

    val current = count
    indirect(nextCount())
    println("Called indirectly, count should be " + (current + 2) + ".  Count:" + count)
  }

  def myMethod(arg: => String) {
    println("1st time: " + arg)
    println("2nd time: " + arg)
  }

  def indirect(arg: => String) {
    // the only way to break this is to assign to an intermediate variable
    myMethod(arg)
  }

  // NOTE: 'call-by-name' not allowed for 'val' args
  case class MyClass()(argSource: => String) {
    lazy val arg = argSource
  }

}