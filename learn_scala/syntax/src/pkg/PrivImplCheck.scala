package pkg


object PrivImplCheck {
  def main(args: Array[String]) {
    val pi = PrivImpl(99)
    println("Should be some strange class: " + pi)
    println("Should be 99: " + pi.s)
  }
}