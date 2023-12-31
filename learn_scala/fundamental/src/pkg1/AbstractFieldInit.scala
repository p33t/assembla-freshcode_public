package pkg1

/**
 * Explore initializing a val in a Trait.  See also [[pkg2.TraitsWithAncestor]] for initializer methods.
 */
trait NeedVal{
  val x: Int
  require(x > 0)
}

object AbstractFieldInit {
  def main(args: Array[String]) {
    var eleven = 11
    var n: NeedVal = null

    // no can do (but should be able to use a literal according to ProgInScala ?!)
//    n = new NeedVal {
//      override val x = 22
//    }

    // no can do... expression evaluated only after the parent class check is done
//    n = new NeedVal {
//      override val x = 2 * eleven
//    }

    // no problem.  An anonymous class that extends the trait
    // NOTE: early initializers will be replaced with Trait params in Scala 3
    n = new {
      val x = 2 * eleven
    } with NeedVal

    // a class that extends an anonymous class first
    // NOTE: early initializers will be replaced with Trait params in Scala 3
    class MyClass extends {
      val x = 2 * eleven
    } with NeedVal
    n = new MyClass

    ///////////////// Alternatively a 'lazy' val might be a simpler fix but requires modification to trait (?)
    // Or use 'def' instead?
  }
}