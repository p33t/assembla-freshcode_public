package pkg.cake2


object Cake2Op {
  // this one is easily unit tested
  def apply(outOfBand: => String)(directArg: String) = {
    outOfBand + "_" + directArg
  }

  trait Trait {
    def outOfBand: String

    val cakeOp = Cake2Op(outOfBand) _
  }
}