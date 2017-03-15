package tendcloud.tianye.userAthuc.servlet

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.{AuthenticationException, IncorrectCredentialsException, UsernamePasswordToken}
import org.apache.shiro.mgt.RealmSecurityManager
import tendcloud.tianye.userAthuc.entity.{Group, User}
import tendcloud.tianye.userAthuc.realm.UserRealm
import tendcloud.tianye.userAthuc.service.{GroupService, PasswordHelper, UserService}
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.CorsSupport
import org.scalatra.json.JacksonJsonSupport

/**
  * Created by tend on 2017/3/6.
  */
class UserAuthServlet (val afterUrl: String, val loginUrl: String)
  extends UserathucStack with JacksonJsonSupport with CorsSupport{

  lazy val userService = new UserService
  lazy val groupService = new GroupService
  lazy val passwordHelper = new PasswordHelper
  protected implicit lazy val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  post("/userLogin") {
    val username = params.getOrElse("username", "")
    val password = params.getOrElse("password", "")
//    val user = new User(username, password)
    println(username+":"+password)

    val currentUser = SecurityUtils.getSubject
//    response.setContentType("text/json")
    response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"))

    try {
      val token = new UsernamePasswordToken(username, password.toCharArray)
//      println(token.getPassword.toString)
      token.setRememberMe(false)
      currentUser.login(token)

//      val userInfo = userService.findByUsername(currentUser.getPrincipal.toString).get
      Map("Login"->0)

    }catch {
      case auex: AuthenticationException => {
        println("userAu:"+auex.toString)
        Map("Login"->1)
      }
    }
  }

  post("/userRegister") {

    val username = params.getOrElse("username", "")
    val password = params.getOrElse("password", "")
    val groupId = params.getOrElse("groupId", "")

    if (userService.findByUsername(username).isDefined) Map("Register"->1)
    else {
      val user = new User(groupId.toLong, username.toString, password.toString)
      userService.createUser(user)
      Map("Register"->0)
    }
  }

  get("/userLogout") {
    SecurityUtils.getSubject.logout()
    Map("Logout"->0)
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
    Map("Change"->0)
  }

  post ("/createGroup") {
    val groupName = params.getOrElse("groupName", "")
    val group = new Group(groupName, 0, "0", true)
    groupService.createGroup(group)
  }

}
