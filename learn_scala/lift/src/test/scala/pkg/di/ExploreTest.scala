package pkg.di

import org.scalatest.Suite
import org.testng.annotations.Test
import org.scala_tools.subcut.inject.{Injectable, NewBindingModule, BindingModule}
import pkg.di.ExploreTest._

@Test
class ExploreTest extends Suite {
  implicit val bm = TheConfig
  val app = new TheApp

  def testBasic() {
    val service = app.service
    expect("hello world") {service.operation()}
  }

  //  def testAltComponent() {
  //    implicit val bm = TheConfig.modifyBindings({
  //      module =>
  //      m.bind[TheExtras].toInstance(new TheExtras{
  //        override def supportMethod() = "bruce"
  //      })
  //    })
  //    val service = b
  //  }
}

object ExploreTest {

  class TheExtra {
    def supportMethod() = "world"
  }

  class TheService(implicit val bindingModule: BindingModule) extends Injectable {
    val extras = injectIfBound[TheExtra](new TheExtra)

    def operation() = {
      "hello " + extras.supportMethod()
    }
  }

  class TheApp(implicit val bindingModule: BindingModule) extends Injectable {
    val service = injectIfBound[TheService](new TheService)
  }

  object TheConfig extends NewBindingModule({
    m =>
  })
}
