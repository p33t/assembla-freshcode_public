package pkg

import pkg.Overriding.MyClass


object Overriding {
  def main(args: Array[String]) {
    val t: MyTrait = new MyClass
    println(t.f(t.s))
  }

  trait MyTrait {
    def s: String

// Not allowed...function values are not defs
//    def f(s: String): String
    def f: (String) => String
  }

  class MyClass extends MyTrait {
    val s: String = "hello"
    val f = (s: String) => s + " world"
  }

}