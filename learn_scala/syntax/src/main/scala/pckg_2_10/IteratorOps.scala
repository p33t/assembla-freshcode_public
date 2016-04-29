package pckg_2_10


object IteratorOps {
  def main(args: Array[String]) {
    val spec = new Special

    val l = List(1,2,3)
    op(l.iterator)

    l.iterator altOp_: spec
  }

  private def op(is: Iterator[Int]) = is.foreach(println)


  class Special {
    // Right associative.  Need to separate alphanumeric from non-alphanumeric with an '_'.
    def altOp_:(is: Iterator[Int]) = is.foreach(println)
  }
}