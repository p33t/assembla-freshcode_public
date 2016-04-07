package pkg.db

import org.squeryl._
import pkg.db.DbTypes._
import pkg.db.model.{BlobEntity, ClobEntity, DirectedEntity}

import scala.reflect.Manifest

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

  val t1 = verTable[T1]

  on(t1)(t => declare(
    t.name is dbType("varchar(256)"),
    t.nicName is dbType("varchar(32)")
  ))
  val custom = table[Custom]
  on(custom)(t => declare(
    t.cust is dbType("varchar(128)")
  ))

  val directedEntity = table[DirectedEntity]

  val clobEntity = table[ClobEntity]
  on(clobEntity)(x => declare(
    x.data is dbType("clob")
  ))

  val blobEntity = table[BlobEntity]
  on(blobEntity)(x => declare(
    x.data is dbType("blob")
  ))

  /**
   * Used for tables with a version field.
   */
  private def verTable[T <: HasIdVer](implicit manifestT: Manifest[T], ked: OptionalKeyedEntityDef[T, _]) = {
    val t = table[T]
    on(t)(t => declare(
      t.ver is uninsertable,
      t.ver defaultsTo 1
    ))
    t
  }
}
