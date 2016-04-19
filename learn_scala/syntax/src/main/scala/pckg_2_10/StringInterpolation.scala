package pckg_2_10

object StringInterpolation {
  def main(args: Array[String]) {
    val theA = "a"
    val theB = "b"
    assert("a=b" == s"$theA=$theB")
  }
}
