package pkg.db.model

import java.io.StringWriter
import java.sql.Types
import java.util.UUID

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.squeryl.Session
import pkg.db.AppSchema
import pkg.db.DbTypes._

@RunWith(classOf[JUnitRunner])
class ClobEntityTest extends FunSuite {
  test("save & restore") {
    val id = UUID.randomUUID()
    val contents = "This is the contents of the clob.  Maybe I should get something that is much longer???"
    AppSchema.init()
    inTransaction {
      AppSchema.reset()
      val ce = ClobEntity(id)
      AppSchema.clobEntity.insert(List(ce, ClobEntity(UUID.randomUUID())))

      // write the contents
      val conn = Session.currentSession.connection
      try {
        val stmt = conn.prepareStatement("update clobentity set data = ? where id = ?")
        val data = conn.createClob()
        val stream = data.setCharacterStream(1)
        stream.write(contents)
        stream.close()
        stmt.setObject(1, data)
        stmt.setObject(2, id)
        stmt.execute()
        data.free()
        assertResult(1)(stmt.getUpdateCount)
      }
    }

    val writer = new StringWriter()
    inTransaction {
      // Not sure how to fix this.  Broken when 0.9.6-RC2 => RC4.  AppSchema.directedEntity.lookup(id) match {
      AppSchema.clobEntity.where(_.id === id).headOption match {
        case None => fail("Unable to locate entity with id " + id)
        case Some(ClobEntity(_, Some(data))) =>
          writer.write(data)
//          val reader = data.getCharacterStream
//          var ch = reader.read()
//          while (ch != -1) {
//            writer.write(ch)
//            ch = reader.read()
//          }
//          reader.close()
      }
    }
    assertResult(contents)(writer.toString)
  }
}