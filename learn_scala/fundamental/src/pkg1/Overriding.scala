package pkg1

/**
 * Confirm overrides behave like Java
 */
object Overriding {
  def main(args: Array[String]) {
    val t: MyTrait = new MyClass
    println(t.f(t.s)) // prints hello world (i.e. dynamically bound / polymorphic)
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