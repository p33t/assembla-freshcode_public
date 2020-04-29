package pkg1

/**
 * Explore overloaded method invocation
 */
object Overloading {
  def main(args: Array[String]) {
    method("Some String")
    method(99)
    // Doesn't work... it's ambiguous
//    method(null)
    method(())
    val sh: Short = 128
    method(sh) // looks like implicits kick in before ambiguous overload.  This uses the 'Int' overload.
    val b: Byte = 22
    method(b) // uses 'Int' overload

    method(true) // uses 'Any' overload
    method(List("one", "two", "buckle", "my", "shoe"))
  }

  def method(any: Any) {
    println("Any method: " + any)
  }

  def method(s: String) {
    println("String method: " + s)
  }

  def method(j: Int) {
    println("Int method: " + j)
  }

  def method[T](l: List[T]) {
    println("List method: " + l)
  }
}
