package pkg2

import java.io.File

/**
 * Assignment does not yield a value (unlike Java, sadly)
 */
object VarAssign {
  def main(args: Array[String]) {
    var v = new File(".")
    val weird = v = new File("..")
    // It is type 'unit'!!
    println(weird.getClass)
  }
}