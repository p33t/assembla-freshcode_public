package pkg


object ForYield {
  val data = List(1, 2, 3, 4) :: List(5, 6, 7, 8) :: List(9, 10) :: Nil

  def main(args: Array[String]) {
    val evens = for {list <- data if list.size > 3
                     num <- list if num % 2 == 0}
      // Note: The yield statement must come immediately after the clauses
    yield "Even number: " + num
    println("Hopefully 2,4,6,8:" + evens)
  }
}