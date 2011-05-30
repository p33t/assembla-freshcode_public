package pkg

import org.scalatest.{FlatSpec}
import java.lang.UnsupportedOperationException
import org.scalatest.matchers.MustMatchers

class SpecTest extends FlatSpec with MustMatchers {

  class Subject {
    def hello = "world"

    def upYours: String = throw new UnsupportedOperationException()
  }

  "A TestSubject" must "respond when greeted" in {
    val s = new Subject()
    s.hello must equal("world")
  }

  it must "avoid escalating conflict" in {
    evaluating {
      val s = new Subject()
      s.upYours
    } must produce[UnsupportedOperationException]
  }
}