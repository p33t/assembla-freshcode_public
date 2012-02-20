package pkg

import net.liftweb.http.auth._
import net.liftweb.sitemap.Loc.HttpAuthProtected
import net.liftweb.common.{Loggable, Full, Empty}

/**
 * Bits of HTTP Digest Auth config that are used by Boot.scala
 */
object DigestAuth extends Loggable {
  private val DrNonPriv = AuthRole("non-privileged")
  private val DrSuperUser = AuthRole("superuser", DrNonPriv)
  // No specific roles required, only need to be logged in
  val DigestAuthenticated = HttpAuthProtected(req => Empty)
  val DigestSuperUser = HttpAuthProtected(req => Full(DrSuperUser))

  val authentication = HttpDigestAuthentication("Password is 's3cret'") {
      case (username, _, authenticates) => {
        logger.info("Authenticating: " + username)
        // NOTE: Need the password in plain text to use digest auth
        if (authenticates("s3cret")) {
          logger.info("Auth succeeded for " + username)
          // set up roles for remainder of request
          val role = {
            if (username == "super") DrSuperUser
            else DrNonPriv
          }
          userRoles(List(role))
          true
        } else {
          logger.warn("Auth failed for " + username)
          false
        }
      }
    }
}