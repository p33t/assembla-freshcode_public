package pkg.db.model

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}
import java.nio.file.{Files, Paths}
import java.util.UUID
import java.util.zip.{GZIPInputStream, GZIPOutputStream}

import org.apache.commons.io.IOUtils
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.squeryl.Session
import pkg.db.AppSchema
import pkg.db.DbTypes._

@RunWith(classOf[JUnitRunner])
class BlobEntityTest extends FunSuite {
  test("save & restore") {
    val id = UUID.randomUUID()
    val contents = {
      val pomXml = Paths.get("pom.xml")
      assert(Files.exists(pomXml))
      Files.readAllBytes(pomXml)
    }
    AppSchema.init()
    inTransaction {
      AppSchema.reset()
      val be = BlobEntity(id)
      AppSchema.blobEntity.insert(List(be, BlobEntity(UUID.randomUUID())))

      // write the contents
      val conn = Session.currentSession.connection
      val stmt = conn.prepareStatement("update blobentity set data = ? where id = ?")
      try {
        val data = conn.createBlob()
        try {
          val gz = new GZIPOutputStream(data.setBinaryStream(1))
          IOUtils.copy(new ByteArrayInputStream(contents), gz)
          gz.close()
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

    val out = new ByteArrayOutputStream()
    inTransaction {
      val conn = Session.currentSession.connection
      val stmt = conn.prepareStatement("select data from blobentity where id = ?")
      try {
        stmt.setObject(1, id)
        assert(stmt.execute(), "Expected a ResultSet")
        val rs = stmt.getResultSet
        assert(rs.next(), "Expected a row")
        val gz = new GZIPInputStream(rs.getBinaryStream(1))
        try {
          IOUtils.copy(gz, out)
          out.flush() // ? Necessary for typical writers?
        } finally {
          gz.close()
        }
        assert(!rs.next(), "Expected no more rows")
      } finally {
        stmt.close()
      }
    }
    assertResult(contents)(out.toByteArray)
  }
}