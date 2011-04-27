package pkg

import scala.actors.Actor._

object ActorDemo {
  def basics: Unit = {
    val act1 = actor {
      while (true) {
        receive {
          case x: Int => println("Integer " + x)
          case "exit" => exit
        }
      }
    }

    act1 ! 99
    act1 ! "bruce lee" // ignored
    act1 ! 100
    act1 ! "exit"
  }

  def useCurrentThread {
    var result:String = null
    self ! "Message in a bottle"
    // safer to use 'receiveWithin' when using current thread
    self.receiveWithin(200) {
      case x: String => result = x
    }
    println(result)
  }

  def main(args: Array[String]) {
    basics
    useCurrentThread
  }
}