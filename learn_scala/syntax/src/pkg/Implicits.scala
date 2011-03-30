package pkg


object Implicits {
  private implicit def intToString(i: Int) = "" + i

  private implicit def boolToInt(b: Boolean) = if (b) 1 else 0

  private implicit def makeLessBoring(b: Boring) = new LessBoring()

  class Unordered(sortVal: Int) {
    // TODO not sure why this is necessary
    def getSortVal = sortVal
  }

  def main(args: Array[String]) {
    // Implicit is applied if it is a singular (non-composite) identifier.  EG. Not SomeClass.intToString
    // By convention one might import all members from a 'Preamble' object for a package EG. import pkg.Preamble._
    // Methods defined on a participating class's 'companion object' are also considered
    printString(10)

    // Will not apply two implicits at a time
    printString(boolToInt(true))
    // Doesn't work...printString(true)

    // can grapht a method onto a different receiver
    val lb = new LessBoring()
    lb.veryUniqueName()
    val b = new Boring()
    b.veryUniqueName()

    // the -> map building syntax is not actually syntax
    // its a method on an implicit that takes any type
    val t = 3 -> "three"
    println(t)

    // implicit args are typically used to adorn normally supplied args
    // and have a very specific type to prevent collisions
    def myMax[A](elements: List[A])(implicit orderer: A => Int): A =
      elements match {
        case List() =>
          throw new IllegalArgumentException("empty list!")
        case List(x) => x
        case x :: rest =>
          val maxRest = myMax(rest)(orderer)
          if (orderer(x) > orderer(maxRest)) x
          else maxRest
      }
    // define the implicit transformer method
    implicit val myOrderer: Unordered => Int = un => un.getSortVal
    val max = myMax(List(new Unordered(3), new Unordered(2), new Unordered(5)))
    println("Should be 5... " + max.getSortVal)
  }

  def printString(s: String) = println(s)
}

class Boring

class LessBoring {
  def veryUniqueName() {
    println("veryUniqueName called")
  }
}




