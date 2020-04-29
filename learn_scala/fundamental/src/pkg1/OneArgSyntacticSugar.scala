package pkg1

/**
 * DSL style.  See also [[FunctionLiterals2]].
 */
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

    c.m1 {
      "bru" + "ce"
    }.m2 {
      "ban" + "ner"
    }
  }
}