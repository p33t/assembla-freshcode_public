package pkg

import obj._

object TypeAliasEg {
  def main(args: Array[String]) {
    // Sadly IDEA doesn't import this naturally like a normal class.  A manual import statement is required.
    val as: AltString = "hello"
    AltStringOps.output(as)
  }
}