package pkg


object Constructors {
  def main(args: Array[String]) {
    val child = new Child("Child Str")
    println("Child.c = " + child.c)
    println("Child.p = " + child.p)


    val child2 = new Child(2)
    println("Child2.p = " + child2.p)
  }

  class Parent(val p: String)
  // parent constructor args are supplied during 'extends' statement
  class Child(val c: String) extends Parent(c + " Parent") {
    // alternate constructors need to feed into primary constructor
    def this(j: Int) = this(j.toString)
  }
}
