package pkg


object Enums {

  object Direction extends Enumeration {
    val North, South = Value
  }

  def main(args: Array[String]) {
    println(Direction.values)
    println(Direction.North)
    val n = Direction(Direction.North.id)
    println("Hopfully north: " + n)
  }
}