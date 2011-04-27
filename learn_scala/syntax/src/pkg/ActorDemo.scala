package pkg

import scala.actors.Actor._

object ActorDemo {
  def main(args: Array[String]) {
    val act1 = actor {
      while (true) {
        receive {
          case x: Int => println("Integer " + x)
        }
      }
    }

    act1 ! 99
    act1 ! "bruce lee" // ignored
    act1 ! 100
  }
}