package pkg

import net.liftweb.http.SessionVar

object CurrentDiyUser {
  private object username extends SessionVar[Option[String]](None)

  def isLoggedIn = username.is != None

  def login(uname: String) {
    username.set(Some(uname))
  }

  def logout() {
    username.set(None)
  }
}