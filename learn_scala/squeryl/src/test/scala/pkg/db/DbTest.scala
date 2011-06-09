package pkg.db

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class DbTest extends FlatSpec with ShouldMatchers {
  "The Db" should "not explode" in {
    pkg.db.Db.withConnection {_ => ()}
  }
}