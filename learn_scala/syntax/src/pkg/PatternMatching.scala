package pkg


object PatternMatching {

  // sealed classes can only be extended by classes in the same file.  It enables compiler support for case class pattern matches
  sealed abstract class TupSeq
  // case clases get factory constructors, automatic 'var'ing of constructor args (they are fields too) and default implementations of equals() and toString()
  case class Terminator() extends TupSeq
  case class Elem[T](head: T, tail: TupSeq) extends TupSeq



  def main(args: Array[String]) {
    val fiver = Elem(5, Elem("four", Elem(3, Elem("two", Elem(1, Terminator())))))
    // auto 'toString()'
    println(fiver)
  }
}