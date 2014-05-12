package pkg.db

import org.squeryl.PrimitiveTypeMode._
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.FunSuite

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
