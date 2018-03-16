package pkg.db.query

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.squeryl.dsl.Measures
import pkg.db.DbTypes.inTransaction
import pkg.db.{AppSchema, T1}

@RunWith(classOf[JUnitRunner])
class ComputeMaxTest extends FunSuite {
  test("calc max") {
    AppSchema.init()
    inTransaction {
      AppSchema.reset()
    }

    def readMax = inTransaction {
      import pkg.db.DbTypes._
      val qry: Measures[Option[Int]] = from(AppSchema.t1)(t => compute(max(t.id))).single
      qry.measures
    }

    assert(readMax.isEmpty)

    inTransaction{
      AppSchema.t1.insert(T1(0, 0, "Bruce Banner", Some("spiderman")))
    }
    // not sure why but it appears id is automatically assigned
    assert(readMax === Some(1))

    inTransaction{
      AppSchema.t1.insert(T1(0, 0, "Bruce Lee", Some("bruise")))
    }
    assert(readMax === Some(2))
  }
}