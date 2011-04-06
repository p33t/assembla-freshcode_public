package pkg


object Abstract {
  def main(args: Array[String]) {
    val c = new Child()
    c.isAbstract("a")
  }

  abstract class Parent {
    def isImplemented(s: String) {
      println("isImplemented called: " + s)
    }

    def isAbstract(s: String)
  }

  class Child extends Parent {
    def isAbstract(s: String) {println("isAbstract called: " + s)}

    //    def isAbstract(s: String) = null // Hmm... should this be an instance of unit and not null?
  }

}
