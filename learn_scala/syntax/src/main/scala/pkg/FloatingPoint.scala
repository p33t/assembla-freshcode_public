package pkg

import java.util.Date


object FloatingPoint {
  def main(args: Array[String]) {
    assert(99.9.toInt == 99)
    assert(scala.math.round(99.9).toInt == 100)
    println("Yay - " + new Date())
  }
}