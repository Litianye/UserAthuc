package tendcloud.tianye.userAthuc.realm

import org.apache.shiro.authz.SimpleAuthorizationInfo
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection
import tendcloud.tianye.userAthuc.service.UserService
import java.util

import org.apache.shiro.authc._
import org.apache.shiro.util.ByteSource
/**
  * Created by tend on 2017/3/5.
  */
class UserRealm extends AuthorizingRealm{
  val userService = new UserService

  override def doGetAuthorizationInfo(principals: PrincipalCollection) = {
    val username = principals.getPrimaryPrincipal.asInstanceOf[String]

    val authorizationInfo = new SimpleAuthorizationInfo
    val roles = userService.findRoles(username)
    val permission = userService.findPermission(username)
    if (roles.nonEmpty){
      authorizationInfo.setRoles(scalaToJavaConverter(roles))
      authorizationInfo.setStringPermissions(scalaToJavaConverter(permission))
    }else {
      println("3.roles empty")
    }
    authorizationInfo
  }

  override def doGetAuthenticationInfo(token: AuthenticationToken): AuthenticationInfo = {
    val username = token.getPrincipal.asInstanceOf[String]
    try {
      val user = userService.findByUsername(username)

      if (user.isEmpty) throw new UnknownAccountException()
      if (user.get.locked.equals(true)) throw new LockedAccountException()

      val authenticationInfo = new SimpleAuthenticationInfo(
        user.get.username,
        user.get.password,
        ByteSource.Util.bytes(user.get.getCredentialSalt()),
        getName
      )
      authenticationInfo
    }catch {
      case uaex: UnknownAccountException => {
        println("Unknown Account Exception")
        new SimpleAuthenticationInfo()
      }
      case laex: LockedAccountException => {
        println("Locked Account Exception")
        new SimpleAuthenticationInfo()
      }
    }
  }

  protected def scalaToJavaConverter(scalaSet: Set[String]): java.util.Set[String] = {
    val javaSet = new util.HashSet[String]()
    scalaSet.foreach(entry => javaSet.add(entry))
    javaSet
  }

  override def clearCachedAuthenticationInfo(principals: PrincipalCollection): Unit =
    super.clearCachedAuthenticationInfo(principals)

  override def clearCachedAuthorizationInfo(principals: PrincipalCollection): Unit =
    super.clearCachedAuthorizationInfo(principals)

  override def clearCache(principals: PrincipalCollection): Unit =
    super.clearCache(principals)

  def clearAllCachedAuthenticationInfo(): Unit = getAuthenticationCache.clear()
  def clearAllCachedAuthorizationInfo(): Unit = getAuthorizationCache.clear()
  def clearAllCache(): Unit = {
    clearAllCachedAuthenticationInfo()
    clearAllCachedAuthorizationInfo()
  }
}
