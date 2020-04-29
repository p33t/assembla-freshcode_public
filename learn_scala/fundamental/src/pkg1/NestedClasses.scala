package pkg1

/**
 *  A simulation of an java static and non-static nested class.
 *  
 *  @see [[pkg1.PathDepTypes]]
 */
object NestedClasses {
  class Outer(msg: String) {
    def hello() = msg

    /** Needs a contextual instance of outer class */
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