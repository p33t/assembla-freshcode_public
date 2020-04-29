package pkg1

/**
 * Examine when default args are evaluated.  They are lazily evaulated so side-effects are not caused when arg is supplied.
 */
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
