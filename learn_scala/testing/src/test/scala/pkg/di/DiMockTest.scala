package pkg.di

import com.borachio.scalatest.MockFactory
import org.scalatest.Suite
import org.scala_tools.subcut.inject.{NewBindingModule, Injectable, BindingModule}
import DiMockTest._

class DiMockTest extends Suite with MockFactory {
  def testConfig() {
    val app = Config.inject(classOf[App], None)
    expect("hello world"){app.service.operation()}
  }
}

object DiMockTest {

  object Config extends NewBindingModule({
    m =>
      // These need to be constructed in dependency order
      m.bind[Deep].toInstance(new Deep()(m))
      m.bind[Service].toInstance(new Service()(m))
      m.bind[App].toInstance(new App()(m))
  })

  class App(implicit val bindingModule: BindingModule) extends Injectable {
    val service = inject[Service]
  }

  class Service(implicit val bindingModule: BindingModule) extends Injectable {
    private val deep = inject[Deep]

    def operation() = "hello " + deep.operation()
  }

  class Deep(implicit val bindingModule: BindingModule) extends Injectable {
    def operation() = "world"
  }

}
