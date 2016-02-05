package pkg.db

import org.junit.runner.RunWith
import org.scalatest.{FunSuite, Spec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class DbTest extends FunSuite {

  test("connect") {
    pkg.db.Db.withConnection {
      _ => ()
    }
  }
}
