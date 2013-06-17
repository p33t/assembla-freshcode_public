package pkg


object ReturnTypeHasWith {

  class SomeBaseClass
  trait SomeTrait

  class SomeSubClass extends SomeBaseClass with SomeTrait

  def main(args: Array[String]) {
    def withMethod(): SomeBaseClass with SomeTrait = {
      new SomeSubClass
    }

    println(withMethod)
  }
}