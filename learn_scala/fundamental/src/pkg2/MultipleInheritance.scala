package pkg2

/**
 * Explore extending multiple traits, each with the same method.  Which one is used?
 */
object MultipleInheritance {

  trait R1 extends Runnable {
    override def run() = {
      println("R1")
    }
  }

  trait R2 extends Runnable {
    override def run() = {
      // Nope... super.run()
      println("R2")
    }
  }

  class Multi extends Runnable with R1 with R2  {
    override def run() = {
      // Looks like this one calls R2
      super.run()
      println("Multi")
    }
  }


  def main(args: Array[String]) {
    new Multi().run()
  }
}