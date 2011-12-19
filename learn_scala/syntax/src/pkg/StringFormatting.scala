package pkg


object StringFormatting {
  def main(args: Array[String]) {
    // Hmmm.... scientific vs decimal is vague: See java.util.Formatter doco
    println("A Double rendered to 17 digit precision in scientific or decimal format (depending): " + "%1$.17g".format(100.00000000000001))
    println("A Double rendered to 17 digit precision in scientific or decimal format (depending): " + "%1$.17g".format(Double.MinValue))
    println("A Double rendered to 17 digit precision in scientific or decimal format (depending): " + "%1$.17g".format(Double.MaxValue))
    println("A Double rendered to hexadecimal floating point: " + "%1$a".format(1.000000001))
    println("A Double rendered to hexadecimal floating point: " + "%1$a".format(Double.MinValue))
    println("A Double rendered to hexadecimal floating point: " + "%1$a".format(Double.MaxValue))

  }
}