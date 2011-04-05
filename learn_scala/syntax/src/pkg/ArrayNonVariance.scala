package pkg

import pkg.Fixture._

object ArrayNonVariance {
  def main(args: Array[String]) {
    val a1 = Array(new SubRoot, new SubRoot)
    val a2: Array[Root] = a1.asInstanceOf[Array[Root]]
    println("Casting is no problem: " + a2.head)
    // NOTE: Casting is enabled because it simulates Java's covariant arrays (compile time).
    // But like java there may be an ArrayStore exception at runtime
    // if targetted array instance is not compatible with an placed element
  }
}