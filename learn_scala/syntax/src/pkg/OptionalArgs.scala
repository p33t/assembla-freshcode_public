package pkg


object OptionalArgs {
  class Cell {
    var value: Int = 0
  }

  var cell: Cell = null

  def setupCell(newCell: Cell = new Cell()) {
    cell = newCell
  }

  def main(args: Array[String]) {
    require(cell == null)
    setupCell()
    val first = cell
    println("First: " + first)
    setupCell()
    val second = cell
    println("Second: " + second)
//    Turns out the defaulting code is called every time!
//    require(first.eq(cell))
  }
}