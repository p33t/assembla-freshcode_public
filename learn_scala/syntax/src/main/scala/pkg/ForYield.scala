package pkg


object ForYield {
  val data = List(1, 2, 3, 4) :: List(5, 6, 7, 8) :: List(9, 10) :: Nil

  def multiClauseComprehension {
    val evens = for {list <- data if list.size > 3
                     num <- list if num % 2 == 0}
    // Note: The yield statement must come immediately after the clauses
    yield "Even number: " + num
    require(List(2, 4, 6, 8).map("Even number: " + _) == evens)
  }

  def optionType {
    def check(o: Option[String]) = {
      for (s <- o) yield s + s
    }

    require(Some("abcabc") == check(Some("abc")))
    require(None == check(None))
  }

  def main(args: Array[String]) {
    multiClauseComprehension
    optionType
    println("No errors: " + this)
  }
}