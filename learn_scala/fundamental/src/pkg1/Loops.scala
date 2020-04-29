package pkg1

/**
 * Exploring while, for and foreach()
 */
object Loops {
  def main(args: Array[String]) {
    val elems = List(2, 4, 6, 8, 0)

    if (elems.isEmpty) println("bad") else println("good")
    println(if (elems.isEmpty) "bad" else "good")

    var i = 0
    while (i < elems.size) {
      print(" " + elems(i))
      i += 1
    }
    println

    // hmmm this doesn't work
//    elems.foreach(print(" " + _))
    elems.foreach(elem => print(" " + elem))
    println

    for (elem <- elems) print(" " + elem)
    println

    // to is inclusive
    for (j <- 0 to 10) print(" " + j)
    println

    // until is exclusive
    for (j <- 0 until 10) print(" " + j)
  }
}