package pkg


object Methods {
  def main(args: Array[String]) {
    val sm = simpleMethod
    println(stringMethod)
  }

  // the absence of '=' means it returns Unit (unambiguously)
  def simpleMethod() {
    println("simple method")
  }

  // by convention, a method without braces has no side affects
  def stringMethod: String = {
    "string method"
  }
}