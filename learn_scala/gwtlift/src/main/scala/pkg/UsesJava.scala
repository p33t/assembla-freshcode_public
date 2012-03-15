package pkg

import biz.freshcode.learn.gwtlift.shared.FieldVerifier

object UsesJava {
  def isValidName(name: String) = FieldVerifier.isValidName(name)
}
