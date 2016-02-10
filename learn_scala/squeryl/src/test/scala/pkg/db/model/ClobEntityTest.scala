package pkg.db.model

import java.io.StringWriter
import java.util.UUID

import org.apache.commons.io.IOUtils
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
      val stmt = conn.prepareStatement("update clobentity set data = ? where id = ?")
      try {
        val data = conn.createClob()
        try {
          val stream = data.setCharacterStream(1)
          stream.write(contents)
          stream.close()
          stmt.setObject(1, data)
          stmt.setObject(2, id)
          stmt.execute()
          assertResult(1)(stmt.getUpdateCount)
        } finally {
          data.free()
        }
      } finally {
        stmt.close()
      }
    }

    val writer = new StringWriter()
    inTransaction {
      // Not sure how to fix this.  Broken when 0.9.6-RC2 => RC4.  AppSchema.directedEntity.lookup(id) match {
      //      AppSchema.clobEntity.where(_.id === id).headOption match {
      //        case None => fail("Unable to locate entity with id " + id)
      //        case Some(ClobEntity(_, Some(data))) =>
      //          writer.write(data)
      //    }

      val conn = Session.currentSession.connection
      val stmt = conn.prepareStatement("select data from clobentity where id = ?")
      try {
        stmt.setObject(1, id)
        assert(stmt.execute(), "Expected a ResultSet")
        val rs = stmt.getResultSet
        assert(rs.next(), "Expected a row")
        val reader = rs.getCharacterStream(1)
        try {
          IOUtils.copy(reader, writer)
        } finally {
          reader.close()
        }
        assert(!rs.next(), "Expected no more rows")
      } finally {
        stmt.close()
      }
    }
    assertResult(contents)(writer.toString)
  }
}