package pkg1

import pkg1.altstring.{AltString, AltStringOps}

object TypeAliasEg {
  def main(args: Array[String]) {
    val as: AltString = "hello"
    AltStringOps.output(as)
  }
}