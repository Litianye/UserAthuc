package tendcloud.tianye.userAthuc.servlet

/**
  * Created by tend on 2017/3/6.
  */
class LoginServlet extends UserathucStack {
  before() {
    contentType = "text/html"
  }

  get("/") {
    ssp("/ssp/login.ssp")
  }

  post("/") {
    ssp("/ssp/login.ssp")
  }

}
