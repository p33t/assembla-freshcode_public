package pkg1

import scala.Stream._

/**
 * Example of dynamically calculated sequence.  LazyList is successor to Streams.
 * Apparently Streams 'head' was not lazy and caused suprises.
 * Personally I don't like the memoization... too easy to slurp everything into memory.   Just use [[Iterator]].
 */
object LazyListAndStreams {
  val LazyNil = LazyList.empty

  def main(args: Array[String]) {
    lazyListEg()
    println()
    streamEg()
  }

  private def lazyListEg() = {
    def myLazyList(i: Int): LazyList[Int] = {
      if (i > 50) LazyList.empty else i #:: myLazyList(i + 2)
    }

    def oddsOnly(src: LazyList[Int]): LazyList[Int] = {
      src match {
        case i +: tail =>
          if (i % 2 == 0) oddsOnly(tail)
          else i +: oddsOnly(tail)
        case LazyNil => LazyNil
      }
    }

    myLazyList(2).foreach(print)
    println()
    oddsOnly((0 until 10).to(LazyList)).foreach(print)
  }
  private def streamEg() = {
    def myStream(i: Int): Stream[Int] = {
      if (i > 50) empty else i #:: myStream(i + 2)
    }

    def oddsOnly(src: Stream[Int]): Stream[Int] = {
      src match {
        case i #:: tail =>
          if (i % 2 == 0) oddsOnly(tail)
          else i #:: oddsOnly(tail)
        case Stream.Empty => Stream.Empty
      }
    }

    myStream(2).foreach(print)
    println()
    oddsOnly((0 until 10).toStream).foreach(print)
  }
}