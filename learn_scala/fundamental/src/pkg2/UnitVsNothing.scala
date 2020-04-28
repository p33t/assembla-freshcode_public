package pkg2

object UnitVsNothing {

  def defUnit[T](t: T = ()): T = {
    t
  }

  class Wrap[T](val t: T = ())

  def main(args: Array[String]): Unit = {
    val noArg = defUnit()
    println("Calling without arg: " + noArg)

    val arg = defUnit("arg-str")
    println("Calling with arg: " + arg)

    val wrapNoArg = new Wrap()
    val wrapArg = new Wrap("arg-str")
      // Hmmm... can't seem to reproduce problem.  I was getting 'Nothing' type elsewhere.
    println("wrapNoArg.t: " + wrapNoArg.t)
    println("wrapArg.t: " + wrapArg.t)
  }
}