package tendcloud.tianye.userAthuc.servlet

import org.apache.shiro.SecurityUtils

/**
  * Created by tend on 2017/3/6.
  */
class SuccessServlet extends UserathucStack{
  before() {
    contentType = "text/html"
    val currentUser = SecurityUtils.getSubject
    println("Success:"+currentUser.getPrincipal)
  }


  get("/") {
    <html>
      <body>
        <h1>Login success</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }

  post("/") {
    <html>
      <body>
        <h1>Login success</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }
}
