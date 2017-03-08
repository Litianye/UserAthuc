package tendcloud.tianye.userAthuc.servlet

/**
  * Created by tend on 2017/3/6.
  */
class ErrorServlet extends UserathucStack{
  before() {
    contentType = "text/html"
  }

  get("/") {
    <html>
      <body>
        <h1>Login error</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }

  post("/") {
    <html>
      <body>
        <h1>Login error</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }

}
