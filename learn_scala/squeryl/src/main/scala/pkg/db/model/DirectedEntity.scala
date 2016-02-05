package pkg.db.model

import java.util.UUID

case class DirectedEntity(id: UUID, dir: DirectionEnum, altDir: Option[DirectionEnum]) {
}
