package pkg1

/**
 * Multiple parameter lists (each with 0 or 1 args)
 */
object ParameterLists {

  def one(s: String) {
    println("Arg1: " + s)
  }

  def oneNone(s:String)() {
    println("Arg1-: " + s)
  }

  def oneOne(s1: String)(s2:String) {
    println(s1 + " and then " + s2)
  }

  def main(args: Array[String]) {
    one("simple")

    val f1 = one _
    f1("Funct Literal") // or is that a function value?

    val f2 = one(_)
    f2("With Braces")

    val f3 = oneNone(_)
    f3("No second arg list") // This IS invoked
//    f3("With empty second arg list")() // does not compile

    val f4 = oneNone _
    f4("Is this any different?") // This is NOT invoked
    f4("_ without braces")()

    val f5 = oneNone(_: String)()
    f5("Removed extra para list?")

    val f6 = oneOne(_:String)(_:String)
    f6("one", "another")

    val f7 = (s1: String, s2: String) => oneOne(s1)(s2)
    f7("nothing", "special")
  }
}