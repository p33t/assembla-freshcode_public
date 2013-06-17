package pkg

import obj.AltString

object AltStringUser {
  def main(args: Array[String]) {
    // it appears we can be totally transparent!  A Pseudo subclass.
    val as = AltString()
    def x(): AltString = null
  }
}