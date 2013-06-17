package pkg

import pkg.Fixture._

object ArrayNonVariance {
  def main(args: Array[String]) {
    val a1 = Array(new Apple, new Apple)
    val a2: Array[Fruit] = a1.asInstanceOf[Array[Fruit]]
    println("Casting is no problem: " + a2.head)
    // NOTE: Casting is enabled because it simulates Java's covariant arrays (compile time).
    // But like java there may be an ArrayStore exception at runtime
    // if targetted array instance is not compatible with an placed element
  }
}