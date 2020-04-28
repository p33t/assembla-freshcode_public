package pkg1

/**
 * Explore mutable and immutable maps and sets.
 */
object Collections {
  def main(args: Array[String]) {
    sets()
    maps()
    useToMap()

    // List append
    println(List(1,2,3) :+ 4)
  }

  def maps() {
    var immut = Map(1 -> "one", 2 -> "two")
    println(immut + (3 -> "three"))
    println(immut)
    immut += (3 -> "three")
    assert(immut.size == 3)
    println(immut)
//    Hmmm... this doesn't work.  Is this a failure of the type system?  Should this work like List and Stack?
//    var immut2 = immut + ("bruce" -> "lee")
//    println(immut2)

    val mut = scala.collection.mutable.Map(1 -> "i", 2 -> "ii")
//    Deprecated syntax
//    mut + (3 -> "iii")
    mut.addOne(3 -> "iii")
    assert(mut.size == 3)
    println(mut)
    // won't work because type parameters of map have been decided
//    mut.addOne("4" -> "iii")
  }

  def sets() {
    var immut = Set("bruce", "lee", "springsteen")
    immut += "willis"
    assert(immut.size == 4)
    println(immut)

    // mutable variants have same (?) methods... but don't need 'var'
    val mut = scala.collection.mutable.Set("raphael", "donatello", "leonardo")
    mut.addOne("michelangelo")
    assert(mut.size == 4)
    println(mut)
  }

  def useToMap() {
    val l = List(MyClass("one", 1), MyClass("two", 2))
    println(l.map{c: MyClass => (c.name, c.value)}.toMap)
  }

  case class MyClass(name: String, value: Int)
}