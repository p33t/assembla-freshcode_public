package pkg


object Collections {
  def main(args: Array[String]) {
    var immutableSet = Set("bruce", "lee", "springsteen")
    immutableSet += "willis"
    println(immutableSet)

    // mutable variants have same (?) methods... but don't need 'var'
    val mutableSet = scala.collection.mutable.Set("raphael", "donatello", "leonardo")
    mutableSet += "michelangelo"
    println(mutableSet)
  }
}