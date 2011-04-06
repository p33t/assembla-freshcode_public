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
  class Child(val c: String) extends Parent(c + " Parent") {
    def this(j: Int) = this(j.toString)
  }
}
