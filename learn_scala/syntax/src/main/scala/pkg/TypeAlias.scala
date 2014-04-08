package pkg


object TypeAlias {
  type CustomString = String

  type BaseType[A, B] = A => B
  type AppliedType = BaseType[Int, String]

  def myPrint(cs: CustomString) {
    println(cs)
  }

  def expectsBaseType[A, B](fn: A => B)(a: A) {
    //  def expectsBaseType[A,B](fn: BaseType[A,B])(a:A) {
    println(fn(a))
  }

  def main(args: Array[String]) {
    myPrint("A normal string")

    val fn: AppliedType = i => "Int: " + i
    //  Need explicit type args
    //    expectsBaseType[Int, String](fn)(99)
    // or declare expectsBaseType... (fn: A => B)...
    // It looks like inputs can only have single layer type aliases.
    expectsBaseType(fn)(99)
  }
}