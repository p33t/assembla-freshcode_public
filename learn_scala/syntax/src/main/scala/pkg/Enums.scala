package pkg

object Enums {

  object Direction extends Enumeration {
    // These are of type Direction.Value
    val North, South = Value
  }

  def varArgs(dirs: Direction.Value*) = {
    dirs.length
  }


  def main(args: Array[String]) {
    println(Direction.values)
    println(Direction.North)
    // Integer id (0-based)
    println(Direction.North.id)
    val n = Direction(Direction.North.id)
    println("Hopefully north: " + n)

    println("Should be three: " + varArgs(Direction.North, Direction.South, Direction.North))
  }
}