package pkg

import org.testng.annotations.Test
import pkg.JsonPolymorphismTest._
import net.liftweb.json.Serialization.{read, write}
import org.joda.time.Period
import org.scalatest.Suite
import net.liftweb.json._
import org.joda.time.format.ISOPeriodFormat

@Test
class JsonPolymorphismTest extends Suite {
  implicit val Formats = Serialization.formats(ShortTypeHints(List(classOf[Dog], classOf[BigCat]))) + PeriodSerializer

  def test() {
    val c = Cage(Dog("spot", Period.days(88)))
    println("c: " + c)
    val ser = write(c)
    println(ser)
    val c2 = read[Cage[Dog]](ser)
    println("c2: " + c2)
    expect(c)(c2)
  }
}

object JsonPolymorphismTest {

  trait Animal

  case class Dog(name: String, age: Period) extends Animal

  case class BigCat(species: String) extends Animal

  case class Cage[T <: Animal](occupant: T)

  object PeriodSerializer extends Serializer[Period] {
    val formatter = ISOPeriodFormat.standard()

    def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), Period] = {
      case (_, js: JString) => formatter.parsePeriod(js.s)
    }

    def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
      case p: Period => JString(formatter.print(p))
    }
  }

}
