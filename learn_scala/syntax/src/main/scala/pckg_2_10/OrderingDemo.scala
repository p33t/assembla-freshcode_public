package pckg_2_10


object OrderingDemo {
  val bruce = Person("bruce")
  val aaron = Person("aaron")
  val before = List(bruce, aaron)
  val after = List(aaron, bruce)

  def main(args: Array[String]): Unit = {
    traitSort()
    objectSortBad()
  }

  def traitSort() {
    implicit val order = new NameOrdTrait[Person]{}
    assert(before.sorted == after)
  }

  def objectSortBad(): Unit = {
    implicit val order = NameOrdObj
// Implicit not found...    assert(before.sorted == after)
  }

  trait Named {
    def name: String
  }

  case class Person(name: String) extends Named

  trait NameOrdTrait[T <: Named] extends Ordering[T] {
    override def compare(x: T, y: T) = x.name.compareTo(y.name)
  }
  
  object NameOrdObj extends NameOrdTrait[Named]
}