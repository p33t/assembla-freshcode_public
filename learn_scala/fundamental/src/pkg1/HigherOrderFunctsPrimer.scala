package pkg1

/**
 * Passing around functions and function literals.
 */
object HigherOrderFunctsPrimer {
  def noArgsToUnit() = println("noArgsToUnit called")
  def noArgsToString() = {"noArgsToString called"}
  def intToString(i: Int) = {"intToString called with arg:" + i}
  def intIntToString(i: Int,j: Int) = {i + " + " + j + " = " + (i + j)}

  def main(args: Array[String]) {
    service(noArgsToUnit)
    service2(noArgsToString)
    service3(intToString)
    service4(intIntToString)
    service3((i: Int) => "Function Literal... " + i)
  }


  def service(clr: () => Unit) {
    clr()
  }

  def service2(clr: () => String) {
    val s = clr()
    println(s)
  }

  def service3(clr: Int => String) {
    val s = clr(44)
    println(s)
  }
  
  def service4(clr: (Int,Int) => String) {
    val s = clr(3, 4)
    println(s)

    // currying (?)
    val clr2 = clr(3, (_: Int))
    service3(clr2)
  }
}
