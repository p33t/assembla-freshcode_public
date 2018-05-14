package pkg


/**
 *  A simulation of an java static inner class.
 *  @see pkg.PathDepTypes
 */
object InnerClassish {
  class Outer(msg: String) {
    def hello() = msg
    class Inner {
      def hi() = Outer.this.hello()
    }
  }

  object Outer {
    class StaticNested {}

    object InnerEnum extends Enumeration {
      val A,B = Value
    }
  }

  def main(args: Array[String]) {
    val outer = new Outer("World")
    val inner = new outer.Inner
    assert(inner.hi() == "World")
    val nestedEnum = Outer.InnerEnum.A
    val nested = new Outer.StaticNested()
    println("No problems: " + this)
  }
}