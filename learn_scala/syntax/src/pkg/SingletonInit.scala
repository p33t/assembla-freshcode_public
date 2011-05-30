package pkg

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
    require(FirstAttempt().theString == "After")
    require(SecondAttempt.second.theString() == "After")
    println(this + " works!")
  }

  class FirstAttempt private (s: String) {
    def theString() = s
  }

  object FirstAttempt {
    private lazy val INSTANCE = new FirstAttempt(param)
    def apply() = INSTANCE
  }

  class SecondAttempt private (s: String) {
    def theString() = s
  }

  object SecondAttempt {
    lazy val second = new SecondAttempt(param)
  }
}