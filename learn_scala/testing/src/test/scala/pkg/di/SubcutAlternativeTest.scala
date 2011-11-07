package pkg.di

import org.scalatest.Suite
import org.scala_tools.subcut.inject._
import Fixtures._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SubcutAlternativeTest extends Suite {

  import SubcutAlternativeTest._

  def testConfig() {
    val app = Config.inject(classOf[App], None)
    expect("hello world") {app.service.operation()}
  }

  def testAltConfig() {
    def check(binds: BindingModule, expected: String) {
      val deep = binds.inject(classOf[Deep], None)
      expect(expected) {
        deep.operation()
      }
    }

    check(Config ~ AltConfig, "world")
    check(AltConfig ~ Config, "bruce")
  }

  def testModifyBindings() {
    object TestBinds extends InitBinds {
      protected def initBinds(m: MutableBindingModule) {
        initAppBinds(m)(this)
        initAltBinds(m)(this) // last one wins
      }
    }

    val app = TestBinds.inject(classOf[App], None)
    expect("hello bruce") {
      app.service.operation()
    }
  }
}

/**
 * Supporting objects / classes. Notes:
 * - The 'InitBinds' trait makes initialising cleaner (less head-hurty)
 * - Initialisation of any injected class happens centrally and only once (unlike the injectIfBound approach)
 * - Factored out initXxxBinds() methods are used directly by tests to create custom bindings
 * - Unfortunately the injected fields must be 'lazy' initialised
 */
object SubcutAlternativeTest {
  ////////////////////////////////// DI Plumbing ///////////////////////////////
  def initAppBinds(m: MutableBindingModule)(implicit outer: BindingModule) {
    m.bind[Deep].toSingleInstance(new DeepImpl("world"))
    m.bind[Service].toSingleInstance(new Service())
    m.bind[App].toSingleInstance(new App())
  }

  def initAltBinds(m: MutableBindingModule)(implicit outer: BindingModule) {
    m.bind[Deep].toSingleInstance(new DeepImpl("bruce"))
  }

  object Config extends InitBinds {
    def initBinds(m: MutableBindingModule) {initAppBinds(m)(this)}
  }

  object AltConfig extends InitBinds {
    protected def initBinds(m: MutableBindingModule) {initAltBinds(m)(this)}
  }

}
