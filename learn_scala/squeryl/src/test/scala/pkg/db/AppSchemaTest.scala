package pkg.db

import org.squeryl.PrimitiveTypeMode._
import org.junit.Test

class AppSchemaTest {

  @Test
  def testSchema() {
    AppSchema.init()
    inTransaction {
      AppSchema.printDdl
      AppSchema.reset()
    }
  }
}
