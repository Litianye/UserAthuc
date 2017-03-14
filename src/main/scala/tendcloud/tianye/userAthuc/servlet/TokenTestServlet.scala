package tendcloud.tianye.userAthuc.servlet

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.{AuthenticationException,UsernamePasswordToken}
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
    val param = params.getOrElse("param", "")

    


  }

}
