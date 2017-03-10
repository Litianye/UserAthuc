package tendcloud.tianye.userAthuc.servlet

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.{AuthenticationException, UsernamePasswordToken}
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json.JacksonJsonSupport
import tendcloud.tianye.userAthuc.entity.User
import tendcloud.tianye.userAthuc.service.UserService

/**
  * Created by tend on 2017/3/10.
  */
class TokenTestServlet extends UserathucStack with JacksonJsonSupport{
  protected implicit lazy val jsonFormats: Formats = DefaultFormats
  val userService = new UserService

  before() {
    contentType = formats("json")
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
      token.setRememberMe(false)
      currentUser.login(token)
//      val userInfo = userService.findByUsername(currentUser.getPrincipal.toString).get
//      Map("Login"->0, "username"->userInfo.username, "group_id"->userInfo.group_id)
      token.toString
    }catch {
      case auex: AuthenticationException => {
        println("userAu:"+auex.toString)
        Map("Login"->1)
      }
    }
  }

}
