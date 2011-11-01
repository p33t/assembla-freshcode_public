package pkg.di

import org.scala_tools.subcut.inject._

/**
 * Facilitates a nicer way of creating BindingModules.
 */
trait InitBinds extends BindingModule {
  private val delegate = new NewBindingModule(initBinds)

  override def bindings = delegate.bindings

  protected def initBinds(m: MutableBindingModule)
}
