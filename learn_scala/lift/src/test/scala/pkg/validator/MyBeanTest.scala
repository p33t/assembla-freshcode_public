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
    val violations = validator.validate(b)
    violations.foreach{
      println(_)
    }
  }
}