package pkg1

/**
 * Define a method that returns a 'with' type
 */
object ReturnTypeHasWith {

  class SomeBaseClass
  trait SomeTrait

  class SomeSubClass extends SomeBaseClass with SomeTrait

  def main(args: Array[String]) {
    def withMethod(): SomeBaseClass with SomeTrait = {
      new SomeSubClass
    }

    println(withMethod()) // prints the 'toString()' from SomeSubClass
  }
}