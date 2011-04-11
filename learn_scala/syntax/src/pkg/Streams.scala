package pkg

import Stream._

object Streams {
  def main(args: Array[String]) {
    def myStream(i: Int): Stream[Int] = {
      if (i > 50) empty else i #:: myStream(i + 2)
    }
    
    myStream(2).foreach(println)
  }
}