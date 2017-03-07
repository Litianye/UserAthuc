package tendcloud.tianye.userAthuc.servlet

/**
  * Created by tend on 2017/3/6.
  */
class ErrorServlet extends UserathucStack{
  before() {
    contentType = "text/html"
  }

  get("/") {
    display404()
  }

  post("/") {
    display404()
  }

  protected def display404() {
    contentType = "text/html"
    ssp("/error/404.ssp")
  }
}
