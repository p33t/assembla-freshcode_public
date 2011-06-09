package pkg.db

import java.sql.{Connection, DriverManager}
import org.h2.jdbcx.JdbcConnectionPool
import pkg.log.Logging

object Db extends Logging {
  log.info("Creating connection Pool.")
  private val pool = JdbcConnectionPool.create("jdbc:h2:mem:appDb", "", "")

  Runtime.getRuntime.addShutdownHook(new Thread(new Runnable {
    def run() {
      pool.dispose()
      log.info("Disposed of DB Connection Pool")
    }
  }))

  def withConnection[T](f: Connection => T): T = {
    val conn = pool.getConnection
    try {
      f(conn)
    }
    finally {
      conn.close()
    }
  }
}