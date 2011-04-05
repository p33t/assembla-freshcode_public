package pkg


object CompanionCheck {
  def main(args: Array[String]) {
    val c: Companion = Companion(10)
    println("Should be 10: " + Companion.getS(c))
    //    println("Shoud not be able to access: " + c.s)
  }
}
