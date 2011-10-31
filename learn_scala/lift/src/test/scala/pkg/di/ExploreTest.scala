package pkg.di

import org.scalatest.Suite
import org.testng.annotations.Test
import org.scala_tools.subcut.inject.{Injectable, NewBindingModule, BindingModule}
import pkg.di.ExploreTest._

@Test
class ExploreTest extends Suite {
  def testBasic() {
    implicit val bm = TheConfig
    val service = new TheService
    expect("hello world"){service.operation()}
  }
}

object ExploreTest {

  class TheExtras {
    def supportMethod() = "world"
  }

  class TheService(implicit val bindingModule: BindingModule) extends Injectable {
    val extras = inject[TheExtras]

    def operation() = {
      "hello " + extras.supportMethod()
    }
  }

  object TheConfig extends NewBindingModule({
    m =>
    m.bind[TheExtras].toClass[TheExtras]
  })

}
