package pkg


object PatternMatching {

  // sealed classes can only be extended by classes in the same file.  It enables compiler support for case class pattern matches
  sealed abstract class TupSeq

  // case clases get factory constructors, automatic 'var'ing of constructor args (they are fields too) and default implementations of equals() and toString()
  case class Terminator() extends TupSeq

  case class Elem[T](head: T, tail: TupSeq) extends TupSeq

  val fiver = Elem(5, Elem("four", Elem(3, Elem("two", Elem(1, Terminator())))))

  def describe(ts: TupSeq) {
    def desc(input: TupSeq): String = input match {
    // `xx` prevents variable assignment and instead looks for a constant or variable
      case `fiver` => "This is the 'fiver'"
      case Elem(h, Terminator()) => "Last elem is " + h
      // assigning a variable name to a more complex part
      case Elem(h, t@Elem(_, Terminator())) => "Second last elem is " + h + " followed by... " + desc(t)
      // guard condition
      case Elem(h, _) if (h.isInstanceOf[String]) => "Found string " + h
      // type matching
      case _: Terminator => "-x"
      // default case
      case _ => "Very boring"
    }

    println(desc(ts))

    ts match {
      case Elem(h, t) => describe(t)
      case _ =>
    }
  }


  def caseClasses {
    // auto 'toString()'
    println(fiver)

    describe(fiver)
  }

  def patternedVals {
    // can deconstruct if we know the structure
    val Elem(fifth, Elem(fourth, tail)) = fiver
    require(fifth == 5)
    require(fourth == "four")
  }

  def forLoopFilters {
    class TupSequenceIterator(t: TupSeq) extends Iterator[TupSeq] {
      var current = t

      // A partial function has an 'isDefinedAt' to test if there is an answer for certain inputs
      def nextPartial: PartialFunction[TupSeq, (TupSeq, TupSeq)] = {
        case t: Terminator => (t, null)
        case e @ Elem(_, t) => (e, t)
      }

      def next() = {
        val (n, newCurrent) = nextPartial(current)
        current = newCurrent
        n
      }

      def hasNext = nextPartial.isDefinedAt(current)
    }

    println("\nThe full list...")
    new TupSequenceIterator(fiver).foreach(println)

    println("\nThere should be no 'Terminator()'...")
    for (Elem(h, t) <- new TupSequenceIterator(fiver)) {
      println(h)
    }
  }

  def main(args: Array[String]) {
    caseClasses
    patternedVals
    forLoopFilters
  }
}