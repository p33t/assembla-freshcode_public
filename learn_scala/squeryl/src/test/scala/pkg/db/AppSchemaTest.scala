package pkg.db

import org.testng.annotations.Test
import org.squeryl.PrimitiveTypeMode._

class AppSchemaTest {

  @Test
  def testSchema() {
    AppSchema.init()
    inTransaction {
      AppSchema.printDdl
    }
  }
}
