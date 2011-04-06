package pkg


object FieldPrivacy {
  def main(args: Array[String]) {

//    Not allowed...
//    val cip = new ClassIsPrivate("bruce")
    val cip = new ClassIsPrivate(4)
    println("Should be 4: " + cip.s)

    val aip = new FieldIsPrivate("bruce")
//    Not allowed...
//    println("Should be 'bruce':" + aip.s)

//    Doesn't work...
//    val pub0 = IsPublic("lee")
    val pub = new IsPublic("lee")
    println("Should be lee: " + pub.s)

    val b = new Boring
    println(b)
  }

  class ClassIsPrivate private(val s:String) {
    def this(i:Int) = this(i.toString)
  }

  class FieldIsPrivate (private val s:String) {
    def this(j: Int) = this(j.toString)
  }

  class IsPublic(val s:String) {
    def this(j:Int) = this(j.toString)
  }

  class Boring
}