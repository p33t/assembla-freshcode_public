package pkg2

/**
 * Explore how to combined and initilise encapsulated components that depend on a common parent trait.
 */
object TraitsWithAncestor {
  abstract class AbstractDb {
    def create(): Unit = println("AbstractDb.create()")

  }

// Causes stack overflow...
//  trait FeatureDb {
//    self: AbstractDb =>
//    override def create(): Unit = {
//      self.create()
//      println("FeatureDb.create()")
//    }
//  }

  trait FeatureDb extends AbstractDb {
    override def create(): Unit = {
      super.create()
      println("FeatureDb.create()")
    }
  }

  trait Feature2Db extends AbstractDb {
    override def create(): Unit = {
      super.create()
      println("Feature2Db.create()")
    }
  }

  object AppDb extends AbstractDb with FeatureDb with Feature2Db {
    override def create(): Unit = {
      super.create()
      println("AppDb.create()")
    }
  }

  def main(args: Array[String]): Unit = {
    AppDb.create()
  }
}