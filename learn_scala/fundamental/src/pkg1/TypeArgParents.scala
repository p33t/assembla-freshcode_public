package pkg1

/**
 * Illustrate covariance, invariance and type args
 */
object TypeArgParents extends App {
  trait AbstractShape {
    def name: String
  }

  class SquareShape(ix: Int) extends AbstractShape {
    override def name: String = "square " + ix
  }

  // Covariant type arg
  case class Wrapper[+T](t: T)

  def method(arg: Wrapper[_ <: AbstractShape]): Unit = {
    println(arg.t.name)
  }

  def method2[T <: AbstractShape](arg: Wrapper[T]): Unit = {
    println(arg.t.name)
  }

  def method3[W <: Wrapper[_ <: AbstractShape]](arg: W): Unit = {
    println(arg.t.name)
  }

  method(Wrapper(new SquareShape(1)))
  method2(Wrapper(new SquareShape(2)))
  method3(Wrapper(new SquareShape(3)))

  var wrapAbs: Wrapper[AbstractShape] = _
  val wrapSqr = Wrapper(new SquareShape(6))
  wrapAbs = wrapSqr // proves covariance

  var map: Map[Wrapper[AbstractShape], AbstractShape] = _
   map = Map(wrapAbs -> new SquareShape(5))
// won't work because key type arg is NOT covariant (but value type arg is)
//  private val mapSqr = Map(wrapSqr -> new SquareShape(5))
  private val mapSqr = Map(wrapSqr.asInstanceOf[Wrapper[AbstractShape]] -> new SquareShape(5))
  map = mapSqr


}