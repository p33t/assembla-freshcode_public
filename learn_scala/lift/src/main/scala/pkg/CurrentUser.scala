package pkg

import net.liftweb.http.SessionVar

object CurrentUser {
  private object username extends SessionVar[Option[String]](None)

  def isLoggedIn = username.is.isDefined

  def login(uname: String) {
    username.set(Some(uname))
  }

  def logout() {
    username.set(None)
  }
}