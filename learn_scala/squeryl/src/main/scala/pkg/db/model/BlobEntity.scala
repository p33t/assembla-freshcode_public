package pkg.db.model

import java.util.UUID

import org.squeryl.KeyedEntity

case class BlobEntity(id: UUID,

                      /**
                        * Option so row can be created with Squeryl without load the string.
                        * Field will be populated manually.
                        */
                      data: Option[Array[Byte]]) extends KeyedEntity[UUID] {
}

object BlobEntity {
  def apply(id: UUID): BlobEntity = BlobEntity(id, None)
}

