package pkg1

import fixture.fruit.{Apple, Fruit, GalaApple, Orange}

object VarianceAnnotations {

  class NonVariantCell[T](init: T) {
    private var value = init

    def getValue = value

    def setValue(newVal: T) = value = newVal
  }

  class CoVariantCell[+T](init: T) {
    // Hmm.. this only compiles if value is instance private!
    private[this] var value = init
    //    private var value = init <<<<<< apparently writable 'value' is contravariant (like input to method)
    private val altValue = init //<<<<< readonly is fine

    def getValue: T = value

    // doens't compile...newVal is contravariant.  See assignValue() below.
    //    def setValue(newVal: T) = value = newVal

    // a contrived example of a lower bound for U
    // just pretend this is List.add() and not a cell.
    def assignValue[U >: T](newVal: U): CoVariantCell[U] = new CoVariantCell(newVal)
  }

  def main(args: Array[String]) {
    val stringOnly = new NonVariantCell("bruce")
    //    Doesn't work...
    //    val anyCell: NonVariantCell[AnyRef] = stringOnly

    var rootPlus = new CoVariantCell(new Fruit)
    val subRootPlus = new CoVariantCell(new Apple)
    rootPlus = subRootPlus
    rootPlus = subRootPlus.assignValue(new Orange)
    println("No Problems")

    functionIllustration()
  }

  def functionIllustration() {
    // Liskov Substitution Principal: Can assume subtype if substitution is satisfactory
    // the val type is a super type of the supplied function literal
    val f: GalaApple => Fruit = (a: Apple) => new Apple
    println("GalaApple in and apple out: " + f(new GalaApple))
    // No can do.
//    f(new Apple)
    f.asInstanceOf[Apple => Apple](new Apple)
  }
}