package tendcloud.tianye.userAthuc.servlet

import org.apache.shiro.SecurityUtils
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json.JacksonJsonSupport
import org.json4s._
import org.scalatra.MatchedRoute
import tendcloud.tianye.userAthuc.service.UserService

/**
  * Created by tend on 2017/3/9.
  */
class GroupServlet extends UserathucStack with JacksonJsonSupport{
  protected implicit lazy val jsonFormats: Formats = DefaultFormats

  lazy val userService = new UserService

//  override def invoke(matchedRoute: MatchedRoute): Option[Any] = {
//    withRouteMultiParams(Some(matchedRoute)) {
//      val operation = params.getOrElse("operation", "")
//      if (operation.equals("user:create")) halt(403, "You are not authorized for the requested client.")
//      else super.invoke(matchedRoute)
//    }
//  }

  before () {
    contentType = formats("json")
    if (!SecurityUtils.getSubject.isAuthenticated) redirect("/login")
  }

  get("/") {
    <html>
      <body>
        <h1>Action Page</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }

  post ("/operationJson/") {
    val operation = parsedBody.extract[OperationInfo]
    val currentUser = SecurityUtils.getSubject
    val username = currentUser.getPrincipal.toString
    val group_id = userService.findByUsername(username).get.group_id

    if (group_id == operation.groupId && currentUser.isPermitted(operation.operationName)) {
      Map("operaJson" -> 0)
    } else {
      Map("operaJson" -> 1)
    }
//    println(operation.operationName, operation.groupId)
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
    if (currentUser.isPermitted(params("operate_name")+":*")) {
      Map("operate"->1)
    }else {
      Map("operate"->0)
    }
  }

}

case class OperationInfo(groupId: Long, operationName: String)