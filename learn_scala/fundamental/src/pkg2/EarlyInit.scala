package pkg2

/**
 * Want a trait val to be initialised using an attribute of a subclass.
 * NOTE: early initializers will be replaced with Trait params in Scala 3
 */
object EarlyInit {

  trait A {
    // NOTE: Cannot have abstract lazy val.
    def foo: String

    val bar = foo + "bar"
  }

  // This doesn't work because 'foo' is null when A initialises
  class B extends A {
    override val foo = "foo"
  }

  // works because A is initialised AFTER parent class
  // NOTE: Early initializers will be deprecated in Scala 3 in favour of trait parameters
  class C extends {
    override val foo = "foo"
  } with A

  trait Core {
    val foo = "fu"
  }

  // works because core in initialised before A
  class D extends Core with A

  // does not work; Core initialised last
  class E extends A with Core

  // somehow, 'lazy' changes the initialisation order (?!)
  class F extends A {
    lazy val foo = "muesli"
  }

  def main(args: Array[String]) {
    println(new B().bar)
    println(new C().bar)
    println(new D().bar)
    println(new E().bar)
    println(new F().bar)
  }
}