package pkg1

/**
 * Examine methods without parens.
 */
class ParensStyle {
  private val x = "ex"

  def emptyParensMethod() {
    // anything with a side affect should have parens to give the clue that something is happening
    println()
  }

  def noParensMethod:String = {
    // benign methods that have no sideaffects don't need parens and could easily be substituted with a field
    // as far as client code is concerned: 'uniform access principal'
    x
  }
}