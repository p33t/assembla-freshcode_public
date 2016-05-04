package pckg_2_10


object IteratorOps {
  def main(args: Array[String]) {
    val spec = new Special

    val l = List(1,2,3)
    op(l.iterator)

    l.iterator altOp_: spec

    // NOTE: this way is more powerful because doesn't require explicit type args
    l.iterator.altOp()

    // specialised subtype methods !!!!!!! taken from TraversableOnce.toMap()
    l.iterator.decrement.altOp()
  }

  private def op(is: Iterator[Int]) = is.foreach(println)

  private implicit def iteratorToSpecial2[T](it: Iterator[T]): Special2[T] = new Special2(it)


  class Special {
    // Right associative.  Need to separate alphanumeric from non-alphanumeric with an '_'.
    def altOp_:(is: Iterator[Int]) = is.foreach(println)
  }

  class Special2[T](it: Iterator[T]) {
      def altOp() = it.foreach(println)

      def decrement(implicit ev: T <:< Int) = it.map(_ - 1)
  }
}