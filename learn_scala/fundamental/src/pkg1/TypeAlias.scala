package pkg1

object TypeAlias {
  type CustomString = String

  type BaseType[A, B] = A => B
  type AppliedType = BaseType[Int, String]

  def myPrint(cs: CustomString) {
    println(cs)
  }

  def expectsAnything[A, B](fn: A => B)(a: A) {
    println(fn(a))
  }

  def expectsBaseType[A, B](fn: BaseType[A, B])(a: A) {
    println(fn(a))
  }

  def main(args: Array[String]) {
    myPrint("A normal string")

    val fn: AppliedType = i => "Int: " + i
    //  Need explicit type args
    expectsBaseType[Int, String](fn)(99)
    expectsAnything(fn)(99)
    expectsBaseType(fn: BaseType[Int, String])(99)
    //    Doesn't compile
    // Looks like type alias is not treated entirely like inheritance
//    expectsBaseType(fn)(99)
  }
}