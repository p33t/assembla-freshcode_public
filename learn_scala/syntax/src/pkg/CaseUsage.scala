package pkg

object CaseUsage {
  def main(args: Array[String]) {
    val l = List((1, "one"), (2, "two"))

    val l2 = l.map {case (i, s) => (s, i)}.mkString("<", "|", ">")
    // Doesn't work... I guess the 'case' technique helps clean up the code
//    val l2 = l.map {(i, s) => (s, i)}.mkString("<", "|", ">")
    val l3 = l.map {t => val (i, s) = t; (s, i)}.mkString("{", ",", "}")
    println(l)
    println(l2)
    println(l3)
  }
}