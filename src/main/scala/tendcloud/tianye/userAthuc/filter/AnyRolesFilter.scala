package tendcloud.tianye.userAthuc.filter

import javax.servlet.http.HttpServletResponse
import javax.servlet.{ServletRequest, ServletResponse}

import org.apache.shiro.web.filter.AccessControlFilter
import org.apache.shiro.web.util.WebUtils

/**
  * Created by tend on 2017/3/15.
  */
class AnyRolesFilter extends AccessControlFilter{
  val loginUrl = "/login"

  override def isAccessAllowed(request: ServletRequest, response: ServletResponse, mappedValue: scala.Any) = {
    val roles: Array[String] = mappedValue.asInstanceOf[Array[String]]
    if (roles.isEmpty) true
    for (role <- roles) {
      if (getSubject(request, response).hasRole(role)) true
    }
    false
  }

  override def onAccessDenied(request: ServletRequest, response: ServletResponse) = {
    val subject = getSubject(request, response)
    if (subject.getPrincipal == null) {
      saveRequest(request)
      WebUtils.issueRedirect(request, response, loginUrl)
    }else {
      WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED)
    }
    false
  }
}
