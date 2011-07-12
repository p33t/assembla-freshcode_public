package pkg


object VarArgs {
  def varArgs(s: String* ) {
    println(s)
  }

  def myArr[T](ts: T*) = {
    ts
  }

  def main(args: Array[String]) {
    varArgs("one", "two", "three")
    val arr = myArr("four", "five", "six")
    varArgs(arr: _*)
  }
}