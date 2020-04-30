package pkg1

/**
 * Trying to initialise a singleton in controlled manner.
 * From http://pbadenski.blogspot.com/2009/06/design-patterns-in-scala-singleton.html
 */
object SingletonInit {
  // It seems we need a mutable var so that something can come and get it.
  var param = "Before"
  def main(args: Array[String]) {
    param = "After"
    // Unfortunately need to put () at each usage.
    require(Singleton1().theString == "After")
    import Singleton2.second
    second // This causes the initialisation 
    require(second.theString() == "After")
    println(this + " works!")
  }

  class Singleton1 private (s: String) {
    def theString() = s
  }

  object Singleton1 {
    private lazy val INSTANCE = new Singleton1(param)
    def apply() = INSTANCE
  }

  class Singleton2 private (s: String) {
    def theString() = s
  }

  object Singleton2 {
    lazy val second = new Singleton2(param)
  }
}