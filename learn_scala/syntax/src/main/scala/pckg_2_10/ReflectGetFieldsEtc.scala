package pckg_2_10


/**
 * Trying to get the fields in declared order (which reflects dependency).
 */
object ReflectGetFieldsEtc {
  def main(args: Array[String]) {
    val inst = new FieldClass
    val cls = inst.getClass
    println(cls.getFields.toList)
    println(cls.getDeclaredFields.toList.map(_.getName))

    println("custom: " + new FieldClass().fieldList.map(fieldDesc))

    def fieldDesc(f: String) = {
      val real = cls.getDeclaredField(f)
      real.setAccessible(true)
      f + ":" + real.get(inst)
    }
  }

  /**
   * Facilitates listing of fields in declared order.
   */
  trait FieldLister {
    def fieldList: List[String] = {
      val lists = stack.map(_.getDeclaredFields.toList)
      build(Nil, lists.map(_.map(_.getName)))
    }

    protected def stack: List[Class[_]] = Nil

    private def build(soFar: List[String], remaining: List[List[String]]): List[String] = {
      remaining match {
        case Nil => soFar
        case first :: Nil => first ::: soFar
        case first :: second :: tail => {
          val local = first.filterNot(second.contains)
          build(local ::: soFar, remaining.tail)
        }
      }
    }
  }

  trait FieldTrait extends FieldLister {
    override protected def stack = new FieldTrait {}.getClass :: super.stack

    val f1 = "Hello"
    val f11 = "Hello again"
  }

  trait FieldTrait2 extends FieldTrait {
    override protected def stack = new FieldTrait2 {}.getClass :: super.stack

    val f2 = "World"
    val f22 = "Worlds"
  }

  trait FieldTrait3 extends FieldTrait2 {
    override protected def stack = new FieldTrait3 {}.getClass :: super.stack

    val f3 = "World"
    val f33 = "Worlds"
  }

  class FieldClass extends FieldTrait3 {
    override protected def stack = classOf[FieldClass] :: super.stack

    val fc = "!"
  }

  class RandomParentClass {
    val youShouldNotSeeThis = "Whoops"
  }

}
