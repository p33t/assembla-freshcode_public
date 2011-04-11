package pkg


object Regexpr {
  def main(args: Array[String]) {
    val bruceLeeRegex = """[B|b]ruce [L|l]ee""".r
    val first = bruceLeeRegex.findFirstIn("bruce willis bruce Lee bruce springsteen")
    first match {
      case Some(s) => println(s)
    }
  }
}