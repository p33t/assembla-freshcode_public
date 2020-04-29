package pkg1

object Regexpr {
  def simple() {
    val bruceLeeRegex = """[B|b]ruce [L|l]ee""".r
    val first = bruceLeeRegex.findFirstIn("bruce willis bruce Lee bruce springsteen")
    first match {
      case Some(s) => println(s)
    }
  }

  def strangeChars() {
    val parens = """\(""".r
    val str = "mary had a (little) lamb"
    val first = parens.findFirstMatchIn(str)
    first match {
      case Some(s) => println("Found: " + str.substring(s.start))
    }
  }

  def numbers() {
    val matchStr = """\d+(\.\d+)?"""
    require("1234".matches(matchStr))
    require("1.12".matches(matchStr))
    require("0".matches(matchStr))
    require(!"abc".matches(matchStr))
    require(!"12 abc".matches(matchStr))
    require(!"abc 1234".matches(matchStr))
    println("Numbers is fine")
  }

  def strange() {
    val reg = """\d+( ?- ?\d+)?"""
    println("Matches: " + "1 - 2".matches(reg))
    println("Matches: " + "1-2".matches(reg))
    // Ahh... turns out it was a different strange character 'EN DASH'
    val s = "-–"
    println("0: " + s(0).toInt)
    println("1: " + s(1).toInt)
  }

  def slash() {
    val reg = "/"
    println("a/b/c".replaceAll(reg, "."))
  }

  def main(args: Array[String]) {
    simple()
    strangeChars()
    numbers()
    strange()
    slash()
  }
}