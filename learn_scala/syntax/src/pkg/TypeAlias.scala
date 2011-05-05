package pkg


object TypeAlias {
  type CustomString = String

  def myPrint(cs: CustomString) {
    println(cs)
  }

  def main(args: Array[String]) {
    myPrint("A normal string")
  }
}