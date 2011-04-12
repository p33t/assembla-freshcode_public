package pkg


object AbstractMembers {

  abstract class Cell {
    // An 'abstract type' member
    type T
    var value: T = _
  }

  class StringCell extends Cell {
    type T = String
  }

  def main(args: Array[String]) {
    val stringCell = new StringCell
    // Thankfully doesn't work
//    stringCell.value = 99
    stringCell.value = "Bruce"
  }
}