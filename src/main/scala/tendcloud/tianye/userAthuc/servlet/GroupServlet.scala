package tendcloud.tianye.userAthuc.servlet

import org.apache.shiro.SecurityUtils
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json.JacksonJsonSupport
import tendcloud.tianye.userAthuc.service.UserService

/**
  * Created by tend on 2017/3/9.
  */
class GroupServlet extends UserathucStack with JacksonJsonSupport{
  protected implicit lazy val jsonFormats: Formats = DefaultFormats

  lazy val userService = new UserService
  before () {
    contentType = "text/html"
    val currentUser = SecurityUtils.getSubject
    if (!currentUser.isAuthenticated) redirect("/login")
  }

  get ("/:groupId") {
//    val username = currentUser.getPrincipal.toString
//    val group_id = userService.findByUsername(username).get.group_id
    <p>Hello, you are in {params("groupId")}</p>
//    if (group_id = params("groupId")) {
//
//    }
  }

}