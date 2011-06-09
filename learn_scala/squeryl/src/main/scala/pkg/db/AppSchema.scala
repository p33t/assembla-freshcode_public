package pkg.db

import org.squeryl.PrimitiveTypeMode._
import org.squeryl.{Session, SessionFactory, Schema}

class T1(val id: Long,
         val name: String,
         val nicName: Option[String]) {
  /**
   * No arg constructor is required when there are nullable fields.
   */
  def this() = this (0, "", Some(""))
}

object AppSchema extends Schema {
  def init() {
    import org.squeryl.adapters.H2Adapter
    // Bootstrap squeryl
    SessionFactory.concreteFactory = Some(() =>
      Session.create(Db.openConnection(), new H2Adapter))
  }

  val t1 = table[T1]

  on(t1)(t => declare(
    t.name is (dbType("varchar(255)")),
    t.nicName is (dbType("varchar(32)"))
  ))
}