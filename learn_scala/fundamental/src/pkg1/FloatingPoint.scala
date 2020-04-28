package pkg1

import java.util.Date

/**
 * Truncating vs rounding a Double
 */
object FloatingPoint {
  def main(args: Array[String]) {
    val d = 99.9
    assert(d.toInt == 99)
    assert(scala.math.round(d).toInt == 100)
    println("Yay - " + new Date())
  }
}