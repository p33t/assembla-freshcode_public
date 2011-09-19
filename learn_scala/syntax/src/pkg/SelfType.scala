package pkg

import pkg.SelfType.Complex


object SelfType {
  def main(args: Array[String]) {
    val complex = new Complex
    complex.alphaOp()
    complex.betaOp()
  }

  trait AlphaComponent {
    this: BetaComponent =>
    def alphaVal: String

    def alphaOp() {println("Alpha op involving " + betaVal)}
  }

  trait BetaComponent {
    this: AlphaComponent =>
    def betaVal: String

    def betaOp() {println("Beta op involving " + alphaVal)}
  }

  /**
   * This class has been broken up into components.  They can be decoupled further if necessary.
   */
  class Complex extends AlphaComponent with BetaComponent {
    val alphaVal = "A1"
    val betaVal = "B1"
  }


}