package pkg.style

/**
 * Summary:
 * At some point the concrete implementation needs to be assigned.
 * If this happens only in one place then great, otherwise....?
 */
object DepsInSameInterface {

  type ImplDetail = () => Unit

  trait Service {
    /**
     * Client traits don't need to supply this but all client classes do.
     * Or there must be a concrete implementation that all clients need to know about.
     * Also, this impl detail has leaked into the service interface.
     * Making it protected might help though.
     */
    val implDetail: ImplDetail

    def featureY() {
      implDetail()
    }
  }

  /**
   * Client needs to be a trait too
   */
  trait Client {
    val service: Service
    def launch() {
      service.featureY()
    }
  }

  /**
   * Actual implementation draws together all implementation details.
   */
  class ActualClient extends Client {
    val service = new Service {
      val implDetail = () => ()
    }
  }

  class ConcreteService extends Service {
    val implDetail = () => ()
  }

  /**
   * Alternatively, the client needs to know the concrete implementation of the service
   */
  class AltClient {
    val service = new ConcreteService()
    def launch() {
      service.featureY()
    }
  }

}
