package pkg.db.model

import java.util.UUID

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.squeryl.customtypes.CustomTypesMode._
import pkg.db.AppSchema
import pkg.db.model.Direction.North

@RunWith(classOf[JUnitRunner])
class DirectedEntityTest extends FunSuite {
 test("save & restore") {
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
        case Some(found) => assertResult(de)(found)
      }
    }
  }
}
