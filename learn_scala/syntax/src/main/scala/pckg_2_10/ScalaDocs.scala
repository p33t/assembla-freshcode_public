package pckg_2_10

/**
 * This is about scala docs.
 * Macros don't work :(
 * $REUSABLE
 *
 * I can reference Scala library [[scala.Option]] or local code [[pkg.Abstract]].
 *
 * {{{
 *   Example code.
 * }}}
 *
 * @see [[pkg.ActorDemo]]
 *
 * @define REUSABLE
 *         This is some reusable documentation.  Good for overloaded methods and the like.
 */
object ScalaDocs {

  /**
   * About to reuse...
   * $REUSABLE
   */
  def method1() {
    println("method1")
  }
}