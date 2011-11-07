package pkg.di

import org.scalatest.Suite
import org.scala_tools.subcut.inject.{Injectable, NewBindingModule, BindingModule}
import pkg.di.SubcutCanonicalTest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SubcutCanonicalTest extends Suite {
  implicit val bm = TheConfig
  val app = new TheApp

  def testBasic() {
    val service = app.service
    expect("hello world") {service.operation()}
  }

  def testObjectRetention() {
    // Looks like 'injectIfBound' does not retaing the dynamically created instance for reuse
    require(app.service.extras != app.altService.extras)
  }

  def testAltComponent() {
    // 'modifyBindings' doesn't do what you'd think.  It doesn't return an altered config.
    // Instead all the test code needs to go into the closure.
    TheConfig.modifyBindings({
      m =>
        m.bind[TheExtra].toInstance(new TheExtra {
          override def supportMethod() = "bruce"
        })

        val testApp = new TheApp()(m)
        expect("hello bruce") {
          testApp.service.operation()
        }
        expect("around the bruce") {
          testApp.altService.operation()
        }
    })
  }
}

object SubcutCanonicalTest {

  class TheExtra {
    def supportMethod() = "world"
  }

  class TheService(implicit val bindingModule: BindingModule) extends Injectable {
    val extras = injectIfBound[TheExtra](new TheExtra)

    def operation() = {
      "hello " + extras.supportMethod()
    }
  }

  class AltService(implicit val bindingModule: BindingModule) extends Injectable {
    val extras = injectIfBound[TheExtra](new TheExtra)

    def operation() = {
      "around the " + extras.supportMethod
    }
  }

  class TheApp(implicit val bindingModule: BindingModule) extends Injectable {
    val service = injectIfBound[TheService](new TheService)
    val altService = injectIfBound[AltService](new AltService)
  }

  object TheConfig extends NewBindingModule({
    m =>
    //    m.bind[TheExtra].toInstance(new TheExtra)
  })

}
