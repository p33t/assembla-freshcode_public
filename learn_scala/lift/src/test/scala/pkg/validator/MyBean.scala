package pkg.validator

import reflect.BeanProperty
import javax.validation.constraints.Size

class MyBean {
  @BeanProperty
  @javax.validation.constraints.NotNull
  @Size(min = 3)
  var stringField: String = ""

  // Non standard constraint
  @org.hibernate.validator.constraints.Range(max=10)
  @BeanProperty
  var intField: Int = 0
}
