package pkg.style

/**
 * Just use Dependency injection already.  Specifically, Subcut.
 */
object PureInterfaces {

  trait Service {
    def feature(): Unit
  }
}