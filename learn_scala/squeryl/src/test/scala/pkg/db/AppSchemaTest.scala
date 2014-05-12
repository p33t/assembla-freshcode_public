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
  }
}
