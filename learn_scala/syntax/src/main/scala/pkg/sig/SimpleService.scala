package convention

object SimpleService {
  /**
    * Some simple service that only has a single method.
    */
  type Sig = Int => String

  /**
    * Factory method.
    */
  def apply(configurationArg: String): Sig = i => i.toString + configurationArg
}
