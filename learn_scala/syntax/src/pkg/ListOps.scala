package pkg

object ListOps {
  def main(args: Array[String]) {
    val l = List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    //Nope... require(l(-1) == 9)
    val slice = l.slice(1, -1)
    val expected = List(1, 2, 3, 4, 5, 6, 7, 8)
    require(slice == expected, slice + " was not " + expected)
  }
}