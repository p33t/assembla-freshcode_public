package pkg


object VarArgs {
  def varArgs(s: String* ) {
    println(s)
  }

  def main(args: Array[String]) {
    varArgs("one", "two", "three")
    val arr = Array("four", "five", "six")
    varArgs(arr: _*)
  }
}