package pkg

import net.liftweb.util.ValueCell


object Cells {
  def main(args: Array[String]) {
    val a = ValueCell(1)
    val b = ValueCell(2)
    val op = b.lift(_ + a)

    println("Should be 3: " + op.get)
    b.set(5)
    println("Should be 6: " + op.get)
    a.set(95)
    println("Should still be 6: " + op.get)
  }
}