package convention

/**
  * Complex service that has multiple methods.
  */
trait ComplexService {
  def method1(arg: Int): String

  def method2(arg: String): Int
}

object ComplexService {
  /**
    * Factory method.
    */
  def apply(configurationArg: String): ComplexService = new Impl(configurationArg)

  class Impl(suffix: String) extends ComplexService {
    /**
      * This field can be overridden by a test fixture.
      */
    protected val simpleService = SimpleService(suffix)

    override def method1(arg: Int) = simpleService(arg)

    override def method2(arg: String) = 0
  }

}
