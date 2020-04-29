package pkg1

/**
 * Attempting to satisfy an abstract method with a function object (unsuccessful)
 *
 * @see [[pkg1.sig.SimpleService]]
 */
object FunctionImplOfTrait {
  def main(args: Array[String]) {
    // normal stuff
    val t1: T1 = new T1 {
      def m1(s: String) = s.length()
    }
    println(t1.m1("12345"))


    val t1Alt: T1 = new T1 {
      val f1: (String) => Int = {
        (s: String) => s.length()
      }
      val m1 = f1; // Does not satisfy but also doesn't seem to interfere <<<<<<<<<<<<<<<<<<<<<<<<

      override def m1(s: String): Int = f1(s)
    }

    println(t1Alt.m1("abc"))
  }


  trait T1 {
    def m1(s: String): Int
  }

}