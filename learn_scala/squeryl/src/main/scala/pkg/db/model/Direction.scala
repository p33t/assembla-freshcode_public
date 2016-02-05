package pkg.db.model

import org.squeryl.customtypes.{CustomType, StringField}
import pkg.db.model.Direction.{North, South}

@deprecated("Was using CustomTypeMode... which I believe is obsolete.")
class Direction(s: String) extends StringField(s) {
  self: CustomType[String] =>

  private lazy val permissions = {
    List(North, South)
      .map(d => d.value -> d)
      .toMap
  }

  /**
   * Permits use of value in a pattern match
   */
  def unapply = permissions.get(value)

}

object Direction {

  case object North extends Direction("N")

  case object South extends Direction("S")

}
