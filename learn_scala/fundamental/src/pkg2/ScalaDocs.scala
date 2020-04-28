package pkg2

/**
 * This is about scala docs.
 * Macros appear to work now...
 * $REUSABLE
 *
 * I can reference Scala library [[scala.Option]] or local code [[fixture.SomeCaseClass]].
 *
 * {{{
 *   Example code.
 * }}}
 *
 * @see [[fixture.SomeClass]]
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