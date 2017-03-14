package tendcloud.tianye.userAthuc.realm

import java.util

import org.apache.shiro.authc.{AuthenticationToken, LockedAccountException, SimpleAuthenticationInfo, UnknownAccountException}
import org.apache.shiro.authz.SimpleAuthorizationInfo
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection
import tendcloud.tianye.userAthuc.service.UserService
import tendcloud.tianye.userAthuc.utils.EncryptionInfo

/**
  * Created by tend on 2017/3/13.
  */
class StatelessRealm extends AuthorizingRealm{
  val userService = new UserService

  override def supports(token: AuthenticationToken): Boolean = {
    token.isInstanceOf[StatelessToken]
  }

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

  override def doGetAuthenticationInfo(token: AuthenticationToken) = {
    val statelessToken: StatelessToken = token.asInstanceOf[StatelessToken]
    val username: String = statelessToken.username
    val key: String =getKey(username)

    //在服务器端生成客户端参数消息摘要
    val serverDigest: String = EncryptionInfo.digest(key, statelessToken.params)

    //然后进行客户端消息摘要和服务器端消息摘要的匹配
    val authenticationInfo = new SimpleAuthenticationInfo(
      username,
      serverDigest,
      getName
    )
    authenticationInfo
  }

  protected def getKey(username: String): String = {
    try {
      val user = userService.findByUsername(username)

      if (user.isEmpty) throw new UnknownAccountException()
      if (user.get.locked.equals(true)) throw new LockedAccountException()

      user.get.password
    }catch {
      case uaex: UnknownAccountException => {
        println("Unknown Account Exception")
        "null"
      }
      case laex: LockedAccountException => {
        println("Locked Account Exception")
        "null"
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
