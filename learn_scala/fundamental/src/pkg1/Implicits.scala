package pkg1

import java.awt.Dimension

// Importing a feature flag means compiler arg is not necessary
// Looks like this is the stepping-stone to deprecation and removal (a-la Scala 3)
import scala.language.implicitConversions


//noinspection SameParameterValue
object Implicits {
  private implicit def intToString(i: Int) = "" + i

  private implicit def boolToInt(b: Boolean) = if (b) 1 else 0

  private implicit def makeLessBoring(b: Boring) = new LessBoring()

  def main(args: Array[String]) {
    typeConversion()
    receiverConversion()
    pseudoSyntax()
    implicitArgList()
    implicitArgListWithDefault()
    viewBounds()
    outOfBandComms()
  }

  def viewBounds() {
    // view bounds ('can treat as a') differ from upper bounds ('is a')
    class Shape {
      val location: Dimension = null
    }
    class Square(val side: Int) extends Shape
    class Rect(val length: Int, val width: Int) extends Shape
    def draw[A <: Shape](s: A) {
      println("Drew a " + s + " at " + s.location)
    }

    implicit def squareToRect(s: Square): Rect = new Rect(s.side, s.side)
    def calcArea[A <% Rect](r: A) {
      // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< EG
      println("Area is " + r.length + " x " + r.width)
    }

    draw(new Shape())
    draw(new Square(3))
    calcArea(new Rect(3, 4))
    calcArea(new Square(4))
  }

  def receiverConversion() {
    // can graft a method onto a different receiver
    val lb = new LessBoring()
    lb.veryUniqueName()
    val b = new Boring()
    // 'makeLessBoring' is implicitly used here
    b.veryUniqueName()

    // implicit conversion operates on parent types too
    val sb = new StillBoring()
    sb.veryUniqueName()

    val sbAlt: LessBoring = sb
    sbAlt.veryUniqueName()
  }

  def typeConversion() {
    // Implicit is applied if it is a singular (non-composite) identifier.  EG. Not SomeClass.intToString
    // By convention one might import all members from a 'Preamble' object for a package EG. import pkg.Preamble._
    // Methods defined on a participating class's 'companion object' are also considered
    printString(10)

    // Will not apply two implicits at a time
    printString(boolToInt(true))
    // Doesn't work...printString(true)
  }

  def pseudoSyntax() {
    // the -> map building syntax is not actually syntax
    // its a method on an implicit that takes any type
    val t = 3 -> "three"
    println(t)
  }

  def implicitArgList() {
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
    implicit val myOrderer: Unordered => Int = un => un.sortVal
    val max = myMax(List(new Unordered(3), new Unordered(2), new Unordered(5)))
    println("Should be 5... " + max.sortVal)
  }

  def implicitArgListWithDefault() {
    def read(arg0: Int)(implicit arg1: String = "bruce"): String = {
      arg1
    }
    assert(read(0) == "bruce")
    assert(read(0)("lee") == "lee")
    implicit val arg1 = "banner"
    assert(read(0) == arg1)
  }

  def outOfBandComms() {
    implicit val ih = new IntHandler(100)
    cleanSignature(1)
  }

  def cleanSignature(i: Int) {
    // No can do... must be resolved at compile time
    println("Out of band comms via implicits doesn't work")
    //    val ih = conjurer
    //    println("Conjured up out of band information to obtain " + (ih.increment + i))
  }

  def conjurer(implicit ih: IntHandler) = {
    ih
  }


  class Unordered(val sortVal: Int)

  def printString(s: String) {println(s)}
}

class IntHandler(val increment: Int)

class Boring

class StillBoring extends Boring

class LessBoring {
  def veryUniqueName() {
    println("veryUniqueName called")
  }
}




