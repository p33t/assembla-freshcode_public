package pkg1

import scala.util.Random

/**
 * Private to the object instance (other instances cannot access, unlike Java private)
 */
object InstancePrivate {

  class C1 {
    private val s = Random.nextString(5)
    // this means private to the object instance
    private[this] val ss = s

    def getS(c:C1) = c.s
    // won't compile...
//    def getSS(c:C1) = c.ss
  }


  def main(args: Array[String]) {
    val c = new C1()
    println("Can getS just fine: " + c.getS(c))
  }
}