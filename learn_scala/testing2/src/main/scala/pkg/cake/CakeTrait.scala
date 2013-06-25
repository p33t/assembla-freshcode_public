package pkg.cake


trait CakeTrait {
  this: CakeTraitSupport =>
  def traitMethod(str: String): Int = {
    // some contrived usage of 'variable'
    if (variable == str) -1
    else str.length
  }
}