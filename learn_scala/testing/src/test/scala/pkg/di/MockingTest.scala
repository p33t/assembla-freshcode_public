package pkg.di

import org.scalatest.Suite
import org.testng.annotations.Test
import com.borachio.scalatest.MockFactory
import org.scala_tools.subcut.inject.{BindingModule, MutableBindingModule}
import Fixtures._

@Test
class MockingTest extends Suite with MockFactory {

  import MockingTest._

  def testConfig() {
    val app = Config.inject(classOf[App], None)
    expect("hello world") {
      app.service.operation()
    }
  }

  def testMocking() {
    // would be nice if this didn't have to be a trait
    val deepMock = mock[Deep]
    deepMock expects 'operation returns "bruce"

    object TestMod extends InitBinds {
      protected def initBinds(m: MutableBindingModule) {
        implicit val bm = this
        Config.bind(m)
        m.bind[Deep] toInstance deepMock
      }
    }

    val app = TestMod.inject(classOf[App], None)
    expect("hello bruce") {
      app.service.operation()
    }
  }
}

object MockingTest {

  object Config extends InitBinds {
    def bind(m: MutableBindingModule)(implicit outer: BindingModule) {
      m.bind[Deep] toInstance (new DeepImpl("world"))
      m.bind[Service] toInstance (new Service())
      m.bind[App] toInstance (new App())
    }

    protected def initBinds(m: MutableBindingModule) {bind(m)(this)}
  }

}
