package pkg.db

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import DbTypes._

@RunWith(classOf[JUnitRunner])
class AppSchemaTest extends FunSuite {

  test("init / reset") {
    AppSchema.init()
    inTransaction {
      AppSchema.printDdl
      AppSchema.reset()
    }

    val t1 = T1(5, 0, "bruce", None)
    inTransaction {
      AppSchema.t1.insert(t1)
    }

    val t1b = t1.copy(name = "Bruce", ver = 1)
    val result = inTransaction {
      AppSchema.t1.update(t1b)
      AppSchema.t1.where(row => row.id === t1.id).headOption
    }

    assert(result == Some(t1b.copy(ver = t1b.ver + 1)))
  }
}
