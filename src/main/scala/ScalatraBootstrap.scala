import org.scalatra._
import javax.servlet.ServletContext

import tendcloud.tianye.userAthuc.servlet._

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new MainServlet, "/index/*")
    context.mount(new LoginServlet, "/login/*")
    context.mount(new UserAuthServlet("/action/", "/error"), "/auth/*")
    context.mount(new ErrorServlet, "/error/*")
    context.mount(new SuccessServlet, "/success/*")
    context.mount(new AdminServlet, "/admin/*")
    context.mount(new GroupServlet, "/action/*")
    context.mount(new TokenTestServlet, "/token/*")
  }
}
