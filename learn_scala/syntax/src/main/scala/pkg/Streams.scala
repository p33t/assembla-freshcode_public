package pkg

import Stream._

object Streams {
  def main(args: Array[String]) {
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
    oddsOnly(0 until 10 toStream).foreach(print)
  }
}