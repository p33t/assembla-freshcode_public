package pkg.di

import org.scala_tools.subcut.inject._

/**
 * Testing fixtures for DI tests.
 * Note that the fields use inject (and not injectIfNotBound) and are 'lazy' initialised.
 */
object Fixtures {

  class App(implicit val bindingModule: BindingModule) extends Injectable {
    // These need to be 'lazy' initialized
    lazy val service = inject[Service]
  }

  class Service(implicit val bindingModule: BindingModule) extends Injectable {
    lazy private val deep = inject[Deep]

    def operation() = "hello " + deep.operation()
  }

  trait Deep {
    def operation(): String
  }

  class DeepImpl(response: String)(implicit val bindingModule: BindingModule) extends Deep with Injectable {
    override def operation() = response
  }

}