package tendcloud.tianye.userAthuc.servlet

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.mgt.RealmSecurityManager
import tendcloud.tianye.userAthuc.entity.User
import tendcloud.tianye.userAthuc.realm.UserRealm
import tendcloud.tianye.userAthuc.service.{PasswordHelper, UserService}

/**
  * Created by tend on 2017/3/6.
  */
class UserAuthServlet (val afterUrl: String, val loginUrl: String)
  extends UserathucStack{

  lazy val userService = new UserService
  lazy val passwordHelper = new PasswordHelper

  before() {
    contentType = "text/html"
  }

  post("/userLogin") {
    val username = params.getOrElse("username", "")
    val password = params.getOrElse("password", "")
    val user = new User(username, password)
//    println(user)

    val currentUser = SecurityUtils.getSubject

    try {
      val token = new UsernamePasswordToken(user.username, user.password.toCharArray)
//      println(token.getPassword.toString)
      token.setRememberMe(true)
      currentUser.login(token)
      redirect(afterUrl)
    }catch {
      case ex: Exception => {
        println(ex.toString)
        redirect(loginUrl)
      }
    }
  }

  post("/userRegister") {
    val username = params.getOrElse("username", "")
    val password = params.getOrElse("password", "")
    val groupId = params.getOrElse("groupId", "")

    val user = new User(groupId.toLong, username.toString, password.toString)
    userService.createUser(user)
    redirect(afterUrl)
  }

  get("/userLogout") {
    SecurityUtils.getSubject.logout()
    redirect(afterUrl)
  }

  post("/userChangePassword") {
    val newPassword = params.getOrElse("newPassword", "")

    val currentUser = SecurityUtils.getSubject
    val userRealm = SecurityUtils.getSecurityManager.asInstanceOf[RealmSecurityManager].
      getRealms.iterator().next().asInstanceOf[UserRealm]
    val user = userService.findByUsername(currentUser.getPrincipal.toString)
//    println("userAuth:"+user.toString)
    userService.changePassword(user.get.id, newPassword)
    userRealm.clearCachedAuthenticationInfo(currentUser.getPrincipals)
//    println("userAuth:"+user.toString)
    redirect("/login")
  }

}
