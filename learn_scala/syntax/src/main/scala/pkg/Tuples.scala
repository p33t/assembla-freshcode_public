package pkg


object Tuples {
  def syntax {
    val t2 = 99 -> "ninety nine"
    println(t2)
    // NOTE: 1 based naming
    println(t2._1 + " -> " + t2._2)

    val t22 = "ninety nine" -> 99
    println(t22)
  }

  def assignment {
    val (a,b) = ("a", "b")
    var c: String = null
    var d: String = null
    // doesn't work
    // http://stackoverflow.com/questions/2776651/scala-tuple-deconstruction/2776847#2776847
//    (c,d) = ("c", "d")
    println((a,b,c,d))
  }

  def main(args: Array[String]) {
    syntax
    assignment
  }
}