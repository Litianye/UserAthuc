package tendcloud.tianye.userAthuc.filter

import java.util
import javax.servlet.{ServletRequest, ServletResponse}
import javax.servlet.http.HttpServletResponse


import org.apache.shiro.web.filter.AccessControlFilter
import tendcloud.tianye.userAthuc.realm.StatelessToken
import tendcloud.tianye.userAthuc.utils.Constants

import scala.collection.immutable.HashMap


/**
  * Created by tend on 2017/3/13.
  */
class StatelessAuthcFilter extends AccessControlFilter{
  override def isAccessAllowed(request: ServletRequest, response: ServletResponse, mappedValue: scala.Any) = false

  override def onAccessDenied(request: ServletRequest, response: ServletResponse) = {
    //1.客户端生成的消息摘要
    val clientDigest: String = request.getParameter(Constants.PARAM_DIGEST)
    //2.客户端传入的用户身份
    val username: String = request.getParameter(Constants.PARAM_USERNAME)
    //3.客户端请求的参数列表
    val params = request.getParameterMap
    params.remove(Constants.PARAM_DIGEST)

    //4.生成无状态Token
    val token: StatelessToken = new StatelessToken(username, params.asInstanceOf[Map[String, _]], clientDigest)

    try {
      //5.委托给Realm进行登录
      getSubject(request, response).login(token)
    }catch {
      case _ =>{
        //6.登录失败
        onLoginFail(response)
        false
      }
    }
    true
  }


  //登录失败时默认返回401状态码
  private def onLoginFail(resposnse: ServletResponse): Unit = {
    val httpResponse = resposnse.asInstanceOf[HttpServletResponse]
    httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED)
    httpResponse.getWriter.write("login error")
  }
}
