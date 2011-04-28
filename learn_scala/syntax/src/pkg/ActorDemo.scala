package pkg

import scala.actors.Actor._
import actors.{TIMEOUT, Actor}

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

  def reactDemo {
    val act = new Actor{
      override def act() {
        // react does not block and returns 'Nothing'
        react {
          case "exit" => println("Goodbye") // no recursion so exit
          case s: String => {
            println("Rec msg: " + s)
            act // tail recurse.  Stack frame is not retained (?)
          }
        }
      }
    }
    act.start
    act ! "Hello"
    act ! "You"
    act ! "exit"
  }

  def loopDemo {
    val act = actor {
      loop {
        react {
          case "exit" => println("Goodbye"); exit
          case s: String => println("Msg : " + s)
        }
      }
    }

    act ! "Loop"
    act ! "Demo"
    act ! "exit"
  }

  def noBlock {
    def remindMeLater {
      val host = self
      actor {
        Thread.sleep(100)
        host ! "reminder"
        println("Reminder has been sent")
      }
    }

    println("Forgetting now...")
    remindMeLater
    self.receive {
      case "reminder" => println("Thanks for the reminder.")
    }
  }

  def withinDemo {
    println("Starting receiveWithin test...")
    self.receiveWithin(1) {
      case TIMEOUT => println("Timeout received")
    }
    println("Finished receiveWithin test.")
  }

  def main(args: Array[String]) {
    basics
    useCurrentThread
    reactDemo
    loopDemo
    noBlock
    withinDemo
  }
}