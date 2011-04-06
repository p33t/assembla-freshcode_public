package pkg


object Collections {
  def main(args: Array[String]) {
    sets
    maps
  }

  def maps: Unit = {
    var immut = Map(1 -> "one", 2 -> "two")
    println(immut + (3 -> "three"))
    println(immut)
    immut += (3 -> "three")
    println(immut)
//    Hmmm... this doesn't work.  Is this a failure of the type system?  Should this work like List and Stack?
//    var immut2 = immut + ("bruce" -> "lee")
//    println(immut2)

    val mut = scala.collection.mutable.Map(1 -> "i", 2 -> "ii")
    mut + (3 -> "iii")
    println(mut)
    // won't work because type parameters of map have been decided
//    mut + ("bruce" -> "willis")
  }

  def sets: Unit = {
    var immut = Set("bruce", "lee", "springsteen")
    immut += "willis"
    println(immut)

    // mutable variants have same (?) methods... but don't need 'var'
    val mut = scala.collection.mutable.Set("raphael", "donatello", "leonardo")
    mut += "michelangelo"
    println(mut)
  }
}