package pkg2

import fixture.SomeCaseClass

/**
 * Obtain a case class' field value via reflection.
 */
object ReflectCaseClassFields {


  def main(args: Array[String]) {
    val cc = SomeCaseClass("bruce")
    val m = classOf[SomeCaseClass].getMethod("str")
    val str = m.invoke(cc)
    println(str)
  }
}
