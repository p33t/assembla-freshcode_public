package pkg.db

import model.DirectedEntity
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.{Session, SessionFactory, Schema}
import org.squeryl.customtypes.{StringField, CustomTypesMode}

//import CustomTypesMode._ ... only needed where 'statements are defined'.  Prevents compile otherwise.

class T1(val id: Long,
         val name: String,
         val nicName: Option[String]) {
  /**
   * No arg constructor is required when there are nullable fields.
   */
// NOT.  def this() = this(0, "", Some(""))
}

// A Custom type.  It indirectly extends CustomType.
class RevString(val str: String) extends StringField(str.reverse)

// Uses a custom type
class Custom(val id: Long, val cust: RevString) {
  def this() = this(0, new RevString(""))
}

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
    t.name is (dbType("varchar(255)")),
    t.nicName is (dbType("varchar(32)"))
  ))

  // With 0.9.6 there is revamped mapping of types.  Date field can map cleanly to Joda time etc.
//  val custom = table[Custom]
//  {
//    import CustomTypesMode._
//    on(custom)(t => declare(
//      t.cust is (dbType("varchar(127)"))
//    ))
//  }

  val directedEntity = table[DirectedEntity]
}
