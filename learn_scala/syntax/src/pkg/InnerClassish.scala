package pkg


/**
 *  A simulation of an java static inner class.
 *  @see pkg.PathDepTypes
 */
object InnerClassish {
  class Outer {
    class Inner
  }

  object Outer {
    class StaticNested {}

    object InnerEnum extends Enumeration {
      val A,B = Value
    }
  }

  def main(args: Array[String]) {
    val outer = new Outer
    val inner = new outer.Inner
    val nestedEnum = Outer.InnerEnum.A
    val nested = new Outer.StaticNested()
    println("No problems: " + this)
  }
}