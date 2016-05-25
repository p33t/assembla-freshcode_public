package pckg_2_10


object EarlyInit {

  trait A {
    def foo: String

    val bar = foo + "bar"
  }

  // This doesn't work because 'foo' is null when A initialises
  class B extends A {
    val foo = "foo"
  }

  // works because A is initialised AFTER parent class
  class C extends {
    val foo = "foo"
  } with A

  trait Core {
    val foo = "fu"
  }

  // works because core in initialised before A
  class D extends Core with A

  // does not work; Core initialised last
  class E extends A with Core

  def main(args: Array[String]) {
    println(new B().bar)
    println(new C().bar)
    println(new D().bar)
    println(new E().bar)
  }
}