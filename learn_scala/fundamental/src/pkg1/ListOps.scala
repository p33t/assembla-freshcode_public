package pkg1

/**
 * Check to see if List.slice() on -ve indicies works
 */
object ListOps {
  def main(args: Array[String]) {
    val l = List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    //Nope... require(l(-1) == 9) ... IndexOutOfBoundsException

    // negative indicies does NOT work
    val slice = l.slice(1, -1)
    val expected = List()
    require(slice == expected, slice + " was not " + expected)
  }
}