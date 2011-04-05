package pkg


object CompanionCheck {
  def main(args: Array[String]) {
//    Looks like a factory method...
    val c: Companion = Companion(10)
    println("Should be 10: " + Companion.getS(c))
    //    println("Shoud not be able to access: " + c.s)
  }
}
