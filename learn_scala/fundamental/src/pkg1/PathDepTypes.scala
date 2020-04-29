package pkg1

/**
 * Similar to Java inner classes (aka non-static, nested class).
 *
 * @see [[pkg1.NestedClasses]]
 */
object PathDepTypes {
  class Outer {
    class Inner
  }

  def main(args: Array[String]) {
//    Doesn't work... need an instance
//    val staticInner = new Outer#InnerEnum
//    println(staticInner)
    val outer1 = new Outer
    val inner1 = new outer1.Inner
    println(inner1)
    val outer2 = new Outer
    var inner2 = new outer2.Inner
//  Doesn't compile
    //    inner2 = inner1

    // the general type
    var inner: Outer#Inner = null
    inner = inner1
    inner = inner2

    // specific type
    var inner22: outer2.Inner = null
    inner22 = inner2
    println("No problems: " + this)
  }
}