package pkg.validator

import org.scalatest.Suite
import javax.validation.Validation
// NOTE: This helps java interop
import collection.JavaConversions._

class MyBeanTest extends Suite {
  val validatorFactory = Validation.buildDefaultValidatorFactory();
  val validator = validatorFactory.getValidator;

  def test() {
    val b = new MyBean
    b.stringField = "a"
    b.intField = 11
    val violations = validator.validate(b)
    expect(2)(violations.size)
    violations.foreach{
      println(_)
    }
  }
}