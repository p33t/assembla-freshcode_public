package pkg1

import pkg1.altstring.{AltString, AltStringOps}

/**
 * Using a type alias.
 */
object AltStringUser {
  def main(args: Array[String]) {
    // it appears we can be totally transparent!  A Pseudo subclass.
    val as = AltString()
    def x(): AltString = null
    AltStringOps.output(as);
  }
}