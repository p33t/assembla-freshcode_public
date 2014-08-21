package pckg_2_10


object OrderingDemo {
  val bruce = Person("bruce")
  val aaron = Person("aaron")
  val before = List(bruce, aaron)
  val after = List(aaron, bruce)

  def main(args: Array[String]): Unit = {
    traitSort()
    clsSort()
    objectSortBad()
  }

  def traitSort() {
    implicit val order = new NameOrdTrait[Person]{}
    assert(before.sorted == after)
  }

  def clsSort() {
    implicit val order = new NameOrdCls[Person]
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

  class NameOrdCls[T <: Named] extends NameOrdTrait[T]

  object NameOrdObj extends NameOrdTrait[Named]
}