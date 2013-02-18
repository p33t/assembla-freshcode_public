package pkg.db

import java.sql.Connection
import org.h2.jdbcx.JdbcConnectionPool
import pkg.log.Logging

object Db extends Logging {
  log.info("Creating connection Pool.")
  private val pool = JdbcConnectionPool.create("jdbc:h2:mem:appDb", "", "")

  def openConnection() = pool.getConnection

  Runtime.getRuntime.addShutdownHook(new Thread(new Runnable {
    def run() {
      pool.dispose()
      log.info("Disposed of DB Connection Pool")
    }
  }))

  def triggerLoad() {
    // nothing
  }

  def withConnection[T](f: Connection => T): T = {
    val conn = openConnection()
    try {
      f(conn)
    }
    finally {
      conn.close()
    }
  }
}
