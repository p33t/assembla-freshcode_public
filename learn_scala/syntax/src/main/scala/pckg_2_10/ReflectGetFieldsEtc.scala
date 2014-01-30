package pckg_2_10

import java.lang.reflect.Field


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

    def fieldDesc(f: Field) = {
      val real = cls.getDeclaredField(f.getName)
      real.setAccessible(true)
      f.getName + ":" + real.get(inst)
    }
  }

  /**
   * Facilitates listing of fields in declared order.
   */
  trait FieldLister {
    def fieldList: List[Field] = {
      build(Nil, stack.map(_.getDeclaredFields.toList))
    }

    protected def stack: List[Class[_]] = Nil

    private def build(soFar: List[Field], remaining: List[List[Field]]): List[Field] = {
      remaining match {
        case Nil => soFar
        case first :: Nil => first ::: soFar
        case first :: second :: tail => {
          val local = subtractTail(first, second)
          build(local ::: soFar, remaining.tail)
        }
      }
    }

    private def subtractTail(master: List[Field], tail: List[Field]) = {
      tail.length match {
        case 0 => master
        case l => master.take(master.length - l)
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
}
