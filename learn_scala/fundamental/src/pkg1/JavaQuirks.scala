package pkg1

/**
 * Beware [[Double.MinValue]] vs [[Double.MinPositiveValue]].
 * And of course [[Double.NaN]] & [[Double.NegativeInfinity]] & [[Double.PositiveInfinity]].
 */
object JavaQuirks {
  def main(args: Array[String]) {
    println("java.lang.Double.MIN_VALUE != Double.MinValue... " + java.lang.Double.MIN_VALUE + " != " + Double.MinValue)
    println("java.lang.Double.MIN_VALUE == Double.MinPositiveValue... " + java.lang.Double.MIN_VALUE + " == " + Double.MinPositiveValue)
  }
}