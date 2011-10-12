package pkg.validator

import org.scalatest.Suite
import org.testng.annotations.Test
import javax.validation.Validation
// NOTE: This helps java interop
import collection.JavaConversions._

@Test
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