package pkg


object PatternMatching {

  // sealed classes can only be extended by classes in the same file.  It enables compiler support for case class pattern matches
  sealed abstract class TupSeq

  // case clases get factory constructors, automatic 'var'ing of constructor args (they are fields too) and default implementations of equals() and toString()
  case class Terminator() extends TupSeq

  case class Elem[T](head: T, tail: TupSeq) extends TupSeq

  def describe(ts: TupSeq) {
    def desc(input: TupSeq): String = input match {
      case Elem(h, Terminator()) => "Last elem is " + h
      // assigning a variable name to a more complex part
      case Elem(h, t@Elem(_, Terminator())) => "Second last elem is " + h + " followed by... " + desc(t)
      // guard condition
      case Elem(h, _) if (h.isInstanceOf[String]) => "Found string " + h
      case Terminator() => "-x"
      // default case
      case _ => "Very boring"
    }

    println(desc(ts))

    ts match {
      case Elem(h, t) => describe(t)
      case _ =>
    }
  }


  def main(args: Array[String]) {
    val fiver = Elem(5, Elem("four", Elem(3, Elem("two", Elem(1, Terminator())))))
    // auto 'toString()'
    println(fiver)

    describe(fiver)
  }
}