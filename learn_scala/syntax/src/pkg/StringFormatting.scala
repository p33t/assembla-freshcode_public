package pkg


object StringFormatting {
  def main(args: Array[String]) {
    // Hmmm.... scientific vs decimal is vague: See java.util.Formatter doco
    val decSci = "%1$.17g"
    println("\nDecimal / Scientific: " + decSci)
    println(decSci.format(100.00000000000001))
    println(decSci.format(Double.MaxValue))
    println(decSci.format(Double.MinPositiveValue))
    println(decSci.format(Double.MinPositiveValue * 2))

    val hex = "%1$a"
    println("\nHexadecimal: " + hex)
    println(hex.format(1.000000001))
    println(hex.format(Double.MaxValue))
    println(hex.format(Double.MinPositiveValue))
    println(hex.format(Double.MinPositiveValue * 2))
  }
}