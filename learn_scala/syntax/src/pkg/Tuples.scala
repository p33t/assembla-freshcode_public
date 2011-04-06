package pkg


object Tuples {
  def main(args: Array[String]) {
    val t2 = 99 -> "ninety nine"
    println(t2)
    // NOTE: 1 based naming
    println(t2._1 + " -> " + t2._2)

    val t22 = "ninety nine" -> 99
    println(t22)
  }
}