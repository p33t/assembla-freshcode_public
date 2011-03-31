package pkg

import java.text.ParseException
import util.Random


object Eithers {

  def operation(): Either[ParseException, Int] = {
    if (Random.nextBoolean) new Left(new ParseException("Failed at " + System.currentTimeMillis, 2))
    else new Right(Random.nextInt())
  }

  def main(args: Array[String]) {
    def f(): String = {
      // hovering over the arg here will indicate the generic type... which is all important
      operation() match {
        case Left(parseEx) => parseEx.getMessage
        case Right(i) => i.toString
      }
    }
    val range = 1 to 100
    for (i <- range) {
      println(f())
    }
  }
}