package pkg1

/**
 * Delaying initialization prevents class setup until a specific method is called.  It has been deprecated.
 */
@deprecated("It is too suprising.")
object DelayedInitEg {

  def main(args: Array[String]) {
    new MyClass
  }

  class MyClass extends DelayedInit {
    println("MyClass has been initialised.")

    def delayedInit(x: => Unit) {
      println("About to init...")
      x // 'MyClass has been initialised.' displays here
      println("Finished init.")
    }
  }

}