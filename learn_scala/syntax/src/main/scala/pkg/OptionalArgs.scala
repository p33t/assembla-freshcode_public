package pkg


object OptionalArgs {
  var counter = 0

  def next() = {
    counter += 1
    counter
  }

  def nextMaybe(arg: Int = next()) = {
    arg
  }

  def main(args: Array[String]) {
    assert(counter == 0)
    val one = nextMaybe()
    assert(one == 1)
    val one2 = nextMaybe(one) // should not cause counter to increment
    assert(one2 == one)
    val two = nextMaybe()
    assert(two == 2)
  }
}
