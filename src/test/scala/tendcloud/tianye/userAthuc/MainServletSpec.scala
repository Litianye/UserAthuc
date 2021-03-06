package tendcloud.tianye.userAthuc

import org.scalatra.test.specs2._
import tendcloud.tianye.userAthuc.servlet.MainServlet

// For more on Specs2, see http://etorreborre.github.com/specs2/guide/org.specs2.guide.QuickStart.html
class MainServletSpec extends ScalatraSpec { def is =
  "GET / on MainServlet"                     ^
    "should return status 200"                  ! root200^
                                                end

  addServlet(classOf[MainServlet], "/*")

  def root200 = get("/") {
    status must_== 200
  }
}
