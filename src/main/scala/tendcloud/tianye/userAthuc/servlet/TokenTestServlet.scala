package tendcloud.tianye.userAthuc.servlet

//import io.igl.jwt._
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.{AuthenticationException, UsernamePasswordToken}
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.CorsSupport
import org.scalatra.json.JacksonJsonSupport
import pdi.jwt._
import tendcloud.tianye.userAthuc.entity.User
import tendcloud.tianye.userAthuc.service.UserService


/**
  * Created by tend on 2017/3/10.
  */
class TokenTestServlet extends UserathucStack with JacksonJsonSupport with CorsSupport{
  protected implicit lazy val jsonFormats: Formats = DefaultFormats
  val userService = new UserService

  before() {
    contentType = "text/html"
  }

  post("/userLogin") {
    val username = params.getOrElse("username", "")
    val password = params.getOrElse("password", "")
    //    val user = new User(username, password)
    println(username + ":" + password)

    val currentUser = SecurityUtils.getSubject
    //    response.setContentType("text/json")
    response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"))

    try {
      val token = new UsernamePasswordToken(username, password.toCharArray)
      //      println(token.getPassword.toString)
      token.setRememberMe(false)
      currentUser.login(token)

      val userInfo = userService.findByUsername(currentUser.getPrincipal.toString).get
      val role = userService.findRoles(userInfo.username)

      val header = JwtHeader(JwtAlgorithm.HS256, "JWT")

      var claim = JwtClaim()
      claim ++= (("username", userInfo.username), ("groupId", userInfo.group_id), ("role", role.head))
      claim = claim.by("tendCloud")
      claim = claim.about("sandBox")
      claim = claim.expiresIn(60*5)
      claim = claim.startsIn(-60*5)

      Jwt.encode(header, claim, userInfo.getCredentialSalt())
    } catch {
      case auex: AuthenticationException => {
        println("userAu:" + auex.toString)
        Map("Login" -> 1)
      }
    }
  }


  post("/verify") {
    val token = request.getHeader("Authorization").drop(7)
    println(token)

    val info = token.split('.')

    if (info.nonEmpty) {
      val json = parse(JwtBase64.decodeString(info(1)))
      val queryInfo = json.extract[tokenInfo]
      val user = userService.findByUsername(queryInfo.username).get

      if (Jwt.isValid(token, user.getCredentialSalt(), Seq(JwtAlgorithm.HS256))) Map{"tokenValid" -> 0}
      else Map{"tokenValid" -> 1}
    }
  }

}

case class tokenInfo(username: String, groupId: Long, role: String)