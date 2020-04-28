package pkg1

/**
 * Overriding various members of a parent class.  Property get/set support!
 */
object AbstractMembers {

  abstract class Cell {
    // An 'abstract type' member
    type T
    var value: T = _
    var val2: T
    val valueString: String = ""
    def valueToString = value.toString
  }

  class StringCell extends Cell {
    type T = String
    // cannot override a 'var' per se.  Can use getter/setter (see below)
//    override def value = "Bruce"
//    override var value: String = "Bruce Willis"

    // method cannot override a val
//    override def valueString = value.toString

    // implementing a method as a val
    override val valueToString = "Custom Override"

    // Can implement a getter and setter to behave like a field
    override def val2 = "Bruce"
    override def val2_=(s: String) = {}
  }

  def main(args: Array[String]) {
    val stringCell = new StringCell
    // Thankfully doesn't work
//    stringCell.value = 99
    stringCell.value = "Bruce"
    assert(stringCell.value == "Bruce")
    assert(stringCell.valueToString == "Custom Override")
  }
}