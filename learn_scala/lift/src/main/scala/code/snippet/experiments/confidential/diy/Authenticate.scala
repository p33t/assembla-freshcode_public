package code.snippet.experiments.confidential.diy

import net.liftweb.util.FieldError
import pkg.CurrentDiyUser
import net.liftweb.http.{S, LiftScreen}


object Authenticate extends LiftScreen {
  val username = field("Username", "", valMinLen(1, "Username cannot be blank"))
  val pwd = password("Password", "")

  protected def finish() {
    val uname = username.is
    CurrentDiyUser.login(uname)
    S.notice("Welcome " + uname)
  }

  override def screenValidate: List[FieldError] = {
    var errs = super.screenValidate
    if (username.is != pwd.is) errs = FieldError(pwd, "Username must be same as password") :: errs
    errs
  }
}