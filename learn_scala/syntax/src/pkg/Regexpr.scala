package pkg


object Regexpr {
  def simple {
    val bruceLeeRegex = """[B|b]ruce [L|l]ee""".r
    val first = bruceLeeRegex.findFirstIn("bruce willis bruce Lee bruce springsteen")
    first match {
      case Some(s) => println(s)
    }
  }

  def strangeChars {
    val parens = """\(""".r
    val str = "mary had a (little) lamb"
    val first = parens.findFirstMatchIn(str)
    first match {
      case Some(s) => println("Found: " + str.substring(s.start))
    }
  }

  def numbers {
    val matchStr = """\d+(\.\d+)?"""
    require("1234".matches(matchStr))
    require("1.12".matches(matchStr))
    require("0".matches(matchStr))
    require(!"abc".matches(matchStr))
    require(!"12 abc".matches(matchStr))
    require(!"abc 1234".matches(matchStr))
    println("Numbers is fine")
  }

  def main(args: Array[String]) {
    simple
    strangeChars
    numbers
  }
}