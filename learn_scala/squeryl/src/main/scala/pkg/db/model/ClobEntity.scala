package pkg.db.model

import java.util.UUID

import org.squeryl.KeyedEntity

case class ClobEntity(id: UUID,

                      /**
                        * Option so row can be created with Squeryl without load the string.
                        * Field will be populated manually.
                        */
                      data: Option[String]) extends KeyedEntity[UUID] {
//  def this() = this(ClobEntity.SampleId, None)
}

object ClobEntity {
//  val SampleId = UUID.randomUUID()

  def apply(id: UUID): ClobEntity = ClobEntity(id, None)
}