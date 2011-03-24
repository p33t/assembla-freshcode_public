package pkg

object Closures {
  def main(args: Array[String]) {
    service(noArgsToUnit)
    service2(noArgsToString)
    service3(dblToString)
  }

  def noArgsToUnit() = println("noArgsToUnit called")
  def noArgsToString() = {"noArgsToString called"}
  def dblToString(d: Double) = {"dblToString called with arg:" + d}

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
}
