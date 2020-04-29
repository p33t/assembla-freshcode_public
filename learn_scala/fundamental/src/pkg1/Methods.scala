package pkg1

object Methods {
  def main(args: Array[String]) {
    val sm = simpleMethod()
    println(sm)
    println(stringMethod)
  }

  // the absence of '=' means it returns Unit (unambiguously)
  // This technique is discouraged nowadays in favour of ...(): Unit = {...
  def simpleMethod() {
    println("simple method")
    44
  }

  // by convention, a method without braces has no side affects
  def stringMethod: String = {
    "string method"
  }
}