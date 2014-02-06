package pckg_2_10

import pckg_2_10.fixture.SomeCaseClass
import scala.reflect.runtime.{universe => ru}

object ReflectCaseClassFields {


  def main(args: Array[String]) {
    val cc = SomeCaseClass("bruce")
    val m = classOf[SomeCaseClass].getMethod("str")
    val str = m.invoke(cc)
    println(str)
  }
}
