package pkg.db

import model.DirectedEntity
import org.squeryl.{Session, SessionFactory, Schema}
import DbTypes._

object AppSchema extends Schema {
  def init() {
    import org.squeryl.adapters.H2Adapter
    // Bootstrap squeryl
    SessionFactory.concreteFactory = Some(() =>
      Session.create(Db.openConnection(), new H2Adapter))
  }

  def reset() {
    drop
    create
  }

  val t1 = table[T1]

  on(t1)(t => declare(
    t.name is dbType("varchar(256)"),
    t.nicName is dbType("varchar(32)")
  ))
  val custom = table[Custom]
  on(custom)(t => declare(
    t.cust is dbType("varchar(128)")
  ))

  val directedEntity = table[DirectedEntity]
}
