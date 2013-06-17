package pkg


class Companion private(private val s:String)

object Companion {
  def apply(i: Int) = {
    // can access the private constructor
    new Companion(i.toString)
  }

  def getS(c: Companion) = {
    // can access the private field
    c.s
  }
}