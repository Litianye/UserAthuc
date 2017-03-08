package tendcloud.tianye.userAthuc.filter

import javax.servlet.{ServletRequest, ServletResponse}

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter

/**
  * Created by tend on 2017/3/8.
  */
class RememberAuthenticationFilter extends FormAuthenticationFilter{
  override def isAccessAllowed(request: ServletRequest, response: ServletResponse, mappedValue: scala.Any): Boolean = {
//    super.isAccessAllowed(request, response, mappedValue)
    val subject = getSubject(request, response)
    if (!subject.isAuthenticated && subject.isRemembered) {
      val session = subject.getSession(true)
      if (session.getAttribute("username") == null) {
        val username = subject.getPrincipal.toString
        session.setAttribute("username", username)
      }
    }
    subject.isAuthenticated || subject.isRemembered
  }

}
