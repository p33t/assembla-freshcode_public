package pkg

object Enums {

  object Direction extends Enumeration {
    // These are of type Direction.Value
    val North, South = Value
  }

  def main(args: Array[String]) {
    println(Direction.values)
    println(Direction.North)
    // Integer id (0-based)
    println(Direction.North.id)
    val n = Direction(Direction.North.id)
    println("Hopfully north: " + n)
  }
}