package pkg

import net.liftweb._
import http._
import common._
import util.Helpers._
import _root_.net.liftweb.mapper._
import _root_.java.sql._

object DbVendor extends ConnectionManager {
  Class.forName("org.h2.Driver");

  def newConnection(name: ConnectionIdentifier) = {
    try {
      // In memory, multi-connect, erase only upon VM close:
      // http://www.h2database.com/html/features.html#in_memory_databases
      Full(DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"))
    } catch {
      case e: Exception =>
        e.printStackTrace()
        Empty
    }
  }

  def releaseConnection(conn: Connection) {
    conn.close()
  }
}
