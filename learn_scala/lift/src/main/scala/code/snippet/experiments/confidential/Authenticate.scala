package code.snippet.experiments.confidential

import net.liftweb.util.FieldError
import pkg.CurrentUser
import net.liftweb.http.{S, LiftScreen}


object Authenticate extends LiftScreen {
  val username = field("Username", "")
  val pwd = password("Password", "")

  protected def finish() {
    val uname = username.is
    CurrentUser.login(uname)
    S.notice("Welcome " + uname)
  }

  override def screenValidate: List[FieldError] = {
    var errs = super.screenValidate
    if (username.is != pwd.is) errs = FieldError(pwd, "Username must be same as password") :: errs
    errs
  }
}