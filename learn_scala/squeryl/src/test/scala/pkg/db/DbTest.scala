package pkg.db

import org.junit.Test


class DbTest {

  @Test
  def testConnect() {
    pkg.db.Db.withConnection {
      _ => ()
    }
  }
}