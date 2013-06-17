package pkg

import pkg.DelayedInitEg.MyClass


object DelayedInitEg {

  def main(args: Array[String]) {
    new MyClass
  }

  class MyClass extends DelayedInit {
    println("MyClass has been initialised.")

    def delayedInit(x: => Unit) {
      println("About to init...")
      x
      println("Finished init.")
    }
  }

}