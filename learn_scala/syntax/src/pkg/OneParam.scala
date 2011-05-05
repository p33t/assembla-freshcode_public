package pkg


object OneParam {

  def one(s: String) {
    println("Arg1: " + s)
  }

  def oneNone(s:String)() {
    println("Arg1-: " + s)
  }

  def main(args: Array[String]) {
    one("simple")

    val f1 = one _
    f1("Funct Literal")

    val f2 = one(_)
    f2("With Braces")

    val f3 = oneNone(_)
    f3("No second arg list") // not invoked!!!!!!!
    f3("With empty second arg list")()

    val f4 = oneNone _
    f4("Is this any different?")
    f4("_ without braces")()

    val f5 = oneNone(_: String)()
    f5("Removed extra para list?")
  }
}