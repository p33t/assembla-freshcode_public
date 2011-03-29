package pkg


object Implicits {
  private implicit def intToString(i: Int) = "" + i
  private implicit def boolToInt(b: Boolean) = if (b) 1 else 0
  private implicit def makeLessBoring(b: Boring) = new LessBoring()


  def main(args: Array[String]) {
    // Implicit is applied if it is a singular (non-composite) identifier.  EG. Not SomeClass.intToString
    // By convention one might import all members from a 'Preamble' object for a package EG. import pkg.Preamble._
    // Methods defined on a participating class's 'companion object' are also considered
    printString(10)

    // Will not apply two implicits at a time
    // Doesn't work...printString(true)

    // can grapht a method onto a different receiver
    val lb = new LessBoring()
    lb.veryUniqueName()
    val b = new Boring()
    b.veryUniqueName()
  }

  def printString(s: String) = println(s)
}

class Boring

class LessBoring {
  def veryUniqueName() {
    println("veryUniqueName called")
  }
}
