package pkg2

/**
 * Methods that have no parenthesis and return Unit are pretty suspicious.
 * Trying to reproduce a "side-effecting nullary methods are discouraged" warning that prevents compilation when -Xfatal-warnings is used.
 * NOTE: Can use -Xlint:-nullary-unit to suppress the warning.  See https://groups.google.com/d/msg/squeryl/XKm9e1_B-QM/aYGH4E2KFwAJ
 */
object NullarySideEffects {

  abstract class AbstractDb {
    //noinspection UnitMethodIsParameterless
    def create: Unit = {
      println("AbstractDb.create")
    }
  }

  class AppDb extends AbstractDb {
    override def create: Unit = {
      super.create
      println("AppDb.create")
    }
  }

  def main(args: Array[String]): Unit = {
    new AppDb().create
  }
}