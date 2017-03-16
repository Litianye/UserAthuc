package tendcloud.tianye.userAthuc.servlet

//import io.igl.jwt._
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.{AuthenticationException, UsernamePasswordToken}
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.CorsSupport
import org.scalatra.json.JacksonJsonSupport
import pdi.jwt.{Jwt, JwtAlgorithm, JwtClaim, JwtHeader}
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

      val jwtToken = Jwt.encode(JwtHeader(JwtAlgorithm.HS256),
        JwtClaim("""{"user":\""""+userInfo.username+"""\"}"""+","+"""{"groupId":\""""+userInfo.group_id+"""\"}"""),
        userInfo.getCredentialSalt())
      jwtToken
//      val jwt = new DecodedJwt(Seq(Alg(Algorithm.HS256), Typ("JWT")), Seq(Iss("tendcloud"), Sub("sandBox")))
//      jwt.encodedAndSigned(userInfo.getCredentialSalt())
//      val jwtToken = DecodedJwt.validateEncodedJwt(
//        "JWT",
//        userInfo.getCredentialSalt(),
//        Algorithm.HS256,
//        Set(Typ),
//        Set(Iss),
//        iss = Some(Iss("tendcloud")),
//        sub = Some(Sub("sandbox"))
//      )
//      jwtToken.toString


    } catch {
      case auex: AuthenticationException => {
        println("userAu:" + auex.toString)
        Map("Login" -> 1)
      }
    }
  }

}