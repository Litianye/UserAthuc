package tendcloud.tianye.userAthuc.filter

import javax.servlet.{ServletRequest, ServletResponse}

import org.apache.shiro.web.servlet.AdviceFilter

/**
  * Created by tend on 2017/3/15.
  */
class MyAdviceFilter extends AdviceFilter{
  override def preHandle(request: ServletRequest, response: ServletResponse): Boolean = {
    super.preHandle(request, response)
  }

  override def postHandle(request: ServletRequest, response: ServletResponse): Unit = {
    super.postHandle(request, response)
  }

  override def afterCompletion(request: ServletRequest, response: ServletResponse, exception: Exception): Unit = {
    super.afterCompletion(request, response, exception)
  }

}
