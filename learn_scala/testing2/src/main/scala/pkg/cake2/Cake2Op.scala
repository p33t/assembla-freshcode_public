package pkg.cake2


object Cake2Op {
  // this one is easily unit tested
  // Note that all out-of-band args are dynamic
  def apply(outOfBand: => String)(directArg: String) = {
    outOfBand + "_" + directArg
  }

  trait Trait {
    def outOfBand: String

    // Could get away with def here but purple is nice effect in client code
    val cakeOp = Cake2Op(outOfBand) _
  }
}
