package pkg


object FunctionImplOfTrait {
  def main(args: Array[String]) {
    // normal stuff
    val t1: T1 = new T1 {
      def m1(s: String) = s.length()
    }
    println(t1.m1("12345"))


    // trying to use function objects............... NOPE
//    val t1Alt: T1 = new T1 {
//      val m1: (String) => Int = {
//        (s: String) => s.length()
//      }
//    }
  }


  trait T1 {
    def m1(s: String): Int
  }

}