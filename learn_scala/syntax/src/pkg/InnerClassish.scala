package pkg


/**
 *  A simulation of an java static inner class.
 *  @see pkg.PathDepTypes
 */
object InnerClassish {
  class Outer {
    class Nested
  }

  object Outer {
    class Inner {}

    object InnerEnum extends Enumeration {
      val A,B = Value
    }
  }

  def main(args: Array[String]) {
    val outer = new Outer
    val nested = new outer.Nested
    val innerEnum = Outer.InnerEnum.A
    val inner = new Outer.Inner()
    println("No problems: " + this)
  }
}