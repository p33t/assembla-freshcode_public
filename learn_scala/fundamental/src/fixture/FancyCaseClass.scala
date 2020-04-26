package fixture

case class FancyCaseClass(str: String, i: Integer, l: Long, str2: String) {
//  This would not be useful... just overload 'apply' on the companion object.
//  def this(str: String, i: Int) {
//    this(str, i, i, str)
//  }
  val alias = str
  lazy val derived = str + " " + str2
}

object FancyCaseClass {
  // alternative case class constructor
  def apply(str: String, i: Int): FancyCaseClass = apply(str, i, i, str)
}
