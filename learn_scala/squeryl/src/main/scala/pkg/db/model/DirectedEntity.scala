package pkg.db.model

import java.util.UUID
import org.squeryl.KeyedEntity

case class DirectedEntity(id: UUID, dir: Direction) extends KeyedEntity[UUID] {
}
