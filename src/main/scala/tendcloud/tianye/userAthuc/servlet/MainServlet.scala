package tendcloud.tianye.userAthuc.servlet

import org.apache.shiro.SecurityUtils

class MainServlet extends UserathucStack {

  before(){
    val currentUser = SecurityUtils.getSubject
    if (currentUser.isAuthenticated) redirect("/success")
  }

  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }

}
