package pkg


object VarianceAnnotations {

  class NonVariantCell[T](init: T) {
    private var value = init

    def getValue = value

    def setValue(newVal: T) = value = newVal
  }

  class CoVariantCell[+T](init: T) {
    // Hmm.. this only compiles if value is instance private!
    private[this] var value = init

    def getValue: T = value

    // doens't compile...
//    def setValue(newVal: T) = value = newVal
  }

  def main(args: Array[String]) {
    val stringOnly = new NonVariantCell("bruce")
    //    Doesn't work...
    //    val anyCell: NonVariantCell[AnyRef] = stringOnly

    var rootPlus = new CoVariantCell(new Root)
    val subRootPlus = new CoVariantCell(new SubRoot)
    rootPlus = subRootPlus // should work (?)
    println("No Problems")
  }


  class Root

  class SubRoot extends Root

  class SubSubRoot extends SubRoot

}