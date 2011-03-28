package pkg

object Closures {
  def main(args: Array[String]) {
    service(noArgsToUnit)
    service2(noArgsToString)
    service3(dblToString)
    service4(intIntToString)
  }

  def noArgsToUnit() = println("noArgsToUnit called")
  def noArgsToString() = {"noArgsToString called"}
  def dblToString(d: Double) = {"dblToString called with arg:" + d}
  def intIntToString(i: Int,j: Int) = {i + " + " + j + " = " + (i + j)}

  def service(clr: () => Unit) = {
    clr()
  }

  def service2(clr: () => String) = {
    val s = clr()
    println(s)
  }

  def service3(clr: Double => String) = {
    val s = clr(java.lang.Math.random())
    println(s)
  }
  
  def service4(clr: (Int,Int) => String) = {
    val s = clr(3, 4)
    println(s)
  }
}
