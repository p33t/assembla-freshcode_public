package pkg.db

import org.junit.Test
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.Suite

@RunWith(classOf[JUnitRunner])
class DbTest extends Suite {

  def testConnect() {
    pkg.db.Db.withConnection {
      _ => ()
    }
  }
}
