package pckg_2_10

import java.io.File

object VarAssign {
  def main(args: Array[String]) {
    var v = new File(".")
    val weird = v = new File("..")
    // It is type 'unit'!!
    println(weird.getClass)
  }
}