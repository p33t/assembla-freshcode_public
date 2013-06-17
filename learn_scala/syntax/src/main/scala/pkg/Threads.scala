package pkg

import actors.Futures
import Futures._

/**
 * Simple threading (Not using actors)
 */
object Threads {
  def main(args: Array[String]) {
    val threadCount = 10
    val sleepLength = 5000L
    val startMillis = System.currentTimeMillis()
    def duration() = System.currentTimeMillis() - startMillis

    val tasks = for (i <- 1 to threadCount) yield future {
      Thread.sleep(sleepLength)
      duration()
    }

    val durations = awaitAll(sleepLength * 2, tasks: _*)

    val total = duration()
    println("Total duration: " + total)
    println("Individual durations: " + durations)
  }
}