package pkg


object AbstractMembers {

  abstract class Cell {
    // An 'abstract type' member
    type T
    var value: T = _
    val valueString: String = ""
    def valueToString = value.toString
  }

  class StringCell extends Cell {
    type T = String
    // cannot override a 'var'
//    override def value = "Bruce"
//    override var value: String = "Bruce Willis"

    // method cannot override a val
//    override def valueString = value.toString

    // implementing a method as a val is supported
    override val valueToString = "Not Supported"
  }

  def main(args: Array[String]) {
    val stringCell = new StringCell
    // Thankfully doesn't work
//    stringCell.value = 99
    stringCell.value = "Bruce"
  }
}