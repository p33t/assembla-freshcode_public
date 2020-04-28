package pkg1

import java.text.ParseException

import scala.util.Random

/**
 * Explore construction and pattern match of Left/Right
 */
object Eithers {

  def operation(): Either[ParseException, Int] = {
    if (Random.nextBoolean) Left(new ParseException("Failed at " + System.currentTimeMillis, 2))
    else Right(Random.nextInt())
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