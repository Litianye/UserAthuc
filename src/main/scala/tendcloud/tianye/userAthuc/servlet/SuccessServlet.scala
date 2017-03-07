package tendcloud.tianye.userAthuc.servlet

/**
  * Created by tend on 2017/3/6.
  */
class SuccessServlet extends UserathucStack{
  before() {
    contentType = "text/html"
  }

  get("/") {
    display200()
  }

  post("/") {
    display200()
  }
  protected def display200(): Unit =  {
    contentType = "text/html"
    ssp("/error/200.ssp")
  }

}
