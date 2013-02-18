package pkg.db

import org.squeryl.PrimitiveTypeMode._
import org.junit.Test
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.Suite

@RunWith(classOf[JUnitRunner])
class AppSchemaTest extends Suite {

  def testSchema() {
    AppSchema.init()
    inTransaction {
      AppSchema.printDdl
      AppSchema.reset()
    }
  }
}
