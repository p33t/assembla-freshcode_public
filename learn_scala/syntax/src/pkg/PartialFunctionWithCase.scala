package pkg


object PartialFunctionWithCase {
  def f1:PartialFunction[Boolean, String] = {
    case e: Boolean => "Answer is " + (if (e) "enabled" else "disabled")
  }


  def main(args: Array[String]) {
    println(f1(true))
  }
}