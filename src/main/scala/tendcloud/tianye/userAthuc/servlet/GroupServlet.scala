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
    contentType = formats("json")
    if (!SecurityUtils.getSubject.isAuthenticated) redirect("/login")
  }

  get ("/group/:groupId") {
    val currentUser = SecurityUtils.getSubject
    val username = currentUser.getPrincipal.toString
    val group_id = userService.findByUsername(username).get.group_id

    if (group_id.toString.equals(params("groupId"))) {
      Map("group"->0, "group_id"->group_id)
    }else {
      Map("group"->1, "real_group_id"->group_id, "target_group_id"->params("groupId"))
    }
  }

  get ("/operation") {
    contentType = "text/html"
    ssp("/ssp/resource.ssp")
  }
  get ("/operate/:operate_name") {
    val currentUser = SecurityUtils.getSubject
    if (currentUser.isPermitted(params("operate_name")+":create")) {
      Map("operate"->1)
    }else {
      Map("operate"->0)
    }
  }

}

case class UserInfo(id: Int, name: String, groupId: Int)