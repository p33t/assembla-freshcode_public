package pkg.cake2


trait Cake2Trait
  // this is like a default implementation that can be overridden in tests.
  // It is better to supply this here and not the client class.
  extends Cake2Op.Trait {

  def traitOp(someArg: String) = {
    cakeOp(someArg)
  }
}