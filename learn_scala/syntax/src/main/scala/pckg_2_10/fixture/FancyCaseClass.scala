package pckg_2_10.fixture


case class FancyCaseClass(str: String, i: Integer, l: Long, str2: String) {
//  This would not be useful... just overload 'apply' on the companion object.
//  def this(str: String, i: Int) {
//    this(str, i, i, str)
//  }
}