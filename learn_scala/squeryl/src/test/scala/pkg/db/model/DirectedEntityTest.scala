package pkg.db.model

import org.scalatest.Suite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import pkg.db.AppSchema
import java.util.UUID
import pkg.db.model.Direction.North
import org.squeryl.customtypes.CustomTypesMode._

@RunWith(classOf[JUnitRunner])
class DirectedEntityTest extends Suite {
  def testSaveRestore() {
    val id = UUID.randomUUID()
    val de = new DirectedEntity(id, North)
    AppSchema.init()
    inTransaction {
      AppSchema.reset()
      AppSchema.directedEntity.insert(de)
    }

    inTransaction {
      AppSchema.directedEntity.lookup(id) match {
        case None => fail("Unable to locate entity with id " + id)
        case Some(found) => expect(de)(found)
      }
    }
  }
}
