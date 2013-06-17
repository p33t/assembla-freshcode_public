package pkg


object OneArgSyntacticSugar {

  class MyClass {
    def m1(msg: String) = {
      println("m1 called with '" + msg + "'")
      this
    }

    def m2(msg: String) = {
      println("m2 called with '" + msg + "'")
      this
    }
  }

  def main(args: Array[String]) {
    val c = new MyClass
    c m1 "bruce" m2 "lee"
  }
}