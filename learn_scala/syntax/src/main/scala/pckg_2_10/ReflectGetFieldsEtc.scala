package pckg_2_10

import java.lang.reflect.Field


/**
 * Trying to get the fields in declared order (which reflects dependency).
 */
object ReflectGetFieldsEtc {
  def main(args: Array[String]) {
    val cls = classOf[FieldClass]
    println(cls.getFields.toList)
    println(cls.getDeclaredFields.toList.map(_.getName))

    println("custom: " + new FieldClass().fieldList.map(_.getName))

  }

  trait FieldLister {
    def fieldList: List[Field] = Nil

    protected def declared: List[Field] = Nil

    protected final def calcDeclared(cls: Class[_]) = {
      cls.getDeclaredFields.toList
    }

    protected final def subtractTail(master: List[Field], tail: List[Field]) = {
      tail.length match {
        case 0 => master
        case l => master.take(master.length - l)
      }
    }
  }

  trait FieldTrait extends FieldLister {
    val f1 = "Hello"
    val f11 = "Hello again"
    private lazy val myFields = calcDeclared(new FieldTrait {}.getClass)

    override def fieldList = {
      val local = subtractTail(myFields, super.declared)
      super.fieldList ::: local
    }

    override protected def declared = myFields
  }

  trait FieldTrait2 extends FieldTrait {
    val f2 = "World"
    val f22 = "Worlds"

    private lazy val myFields = calcDeclared(new FieldTrait2 {}.getClass)

    override def fieldList = {
      val local = subtractTail(myFields, super.declared)
      super.fieldList ::: local
    }

    override protected def declared = myFields
  }

  trait FieldTrait3 extends FieldTrait2 {
    val f3 = "World"
    val f33 = "Worlds"

    private lazy val myFields = calcDeclared(new FieldTrait3 {}.getClass)

    override def fieldList = {
      val local = subtractTail(myFields, super.declared)
      super.fieldList ::: local
    }

    override protected def declared = myFields  }

  class FieldClass extends FieldTrait3 {
    val fc = "!"
  }

}
