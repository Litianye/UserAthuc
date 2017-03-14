package tendcloud.tianye.userAthuc.realm

import org.apache.shiro.authc.AuthenticationToken

/**
  * Created by tend on 2017/3/13.
  */
class StatelessToken extends AuthenticationToken{
  var username: String = _
  var params: Map[String, _] = _
  var clientDigest: String = _

  def this (username: String, params: Map[String, _], clientDigest: String) {
    this()
    this.username = username
    this.params = params
    this.clientDigest = clientDigest
  }

  override def getPrincipal = username

  override def getCredentials = clientDigest
}
