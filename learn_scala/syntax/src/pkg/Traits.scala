package pkg


class Fruit(val name: String) {
  override def toString = name
}

// NOTE: Traits cannot have class params (like Fruit)
trait EasyPeel {
  override def toString = {super.toString + " is easy to peel"}
}
trait GrowsOnTree {
  override def toString = {super.toString + " grows on a tree"}
}

class Apple extends Fruit("apple") with GrowsOnTree
class Orange extends Fruit("orange") with GrowsOnTree with EasyPeel

object Traits {
  def main(args: Array[String]) {
    val g: GrowsOnTree = new Apple
    println(g)
    println(new Orange)
  }
}