import org.scalatra._
import javax.servlet.ServletContext

import tendcloud.tianye.userAthuc.servlet._

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new MainServlet, "/index/*")
    context.mount(new LoginServlet, "/login/*")
    context.mount(new UserAuthServlet("/success", "/error"), "/auth/*")
    context.mount(new ErrorServlet, "/error")
    context.mount(new SuccessServlet, "/success")
  }
}
