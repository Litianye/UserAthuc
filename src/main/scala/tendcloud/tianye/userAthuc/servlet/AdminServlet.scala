package tendcloud.tianye.userAthuc.servlet

import org.apache.shiro.SecurityUtils

/**
  * Created by tend on 2017/3/7.
  */
class AdminServlet extends UserathucStack{
  before() {
    contentType = "text/html"
    val currentUser = SecurityUtils.getSubject
    if (! currentUser.isAuthenticated) ssp("/ssp/login")
    if (! currentUser.hasRole("admin")) redirect("/error")
//    if (! currentUser.isPermitted("group:*")) redirect("/error")
  }

  get("/") {
    <html>
      <body>
        <h1>Admin Page</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }

  post("/") {
    <html>
      <body>
        <h1>Admin Page</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }

}
