package tendcloud.tianye.userAthuc.credentials

import java.util.concurrent.atomic.AtomicInteger

import org.apache.shiro.authc._
import org.apache.shiro.authc.credential.HashedCredentialsMatcher
import org.apache.shiro.cache.{Cache, CacheManager}

/**
  * Created by tend on 2017/3/6.
  */
class UserHashedCredentialsMatcher extends HashedCredentialsMatcher{

//  var passwordRetryCache: Cache[String, AtomicInteger] = _
//
//  def this(cacheManager: CacheManager) {
//    this()
//    passwordRetryCache = cacheManager.getCache("passwordRetryCache")
//  }

  override def doCredentialsMatch(token: AuthenticationToken, info: AuthenticationInfo): Boolean = {

//    val username = token.getPrincipal.asInstanceOf[String]
//    try {
//      var retryCount = passwordRetryCache.get(username)
//      if (retryCount == null) {
//        retryCount = new AtomicInteger(0)
//        passwordRetryCache.put(username, retryCount)
//      }
//      println(retryCount.toString)
//      if (retryCount.incrementAndGet() > 5) throw new ExcessiveAttemptsException()
//      val matches: Boolean =
    try{
      super.doCredentialsMatch(token, info)
    }catch {
      case ilex: IllegalArgumentException => {
        println("credentials:"+ilex.toString)
        false
      }
    }

//    val tokenHashedCredentials = hashProvidedCredentials(token, info)
//    println("tk:"+tokenHashedCredentials.getClass.toString+":"+tokenHashedCredentials)
//    val accountCredentials = getCredentials(info)
//    println("ac:"+accountCredentials.getClass.toString+":"+accountCredentials)
//    equals(tokenHashedCredentials, accountCredentials)
//      if (matches) {
    //        passwordRetryCache.remove(username)
    //      }
    //      matches
    //    }catch {
    //      case eaex:ExcessiveAttemptsException => {
    //        println("ExcessiveAttemptsException")
    //        false
    //      }
    //    }
  }

}