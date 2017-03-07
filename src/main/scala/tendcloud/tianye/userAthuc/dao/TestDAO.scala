package tendcloud.tianye.userAthuc.dao

import org.mybatis.scala.config.Configuration

/**
  * Created by tend on 2017/3/5.
  */
object TestDAO {
  val config = Configuration("mybatis.xml")
  config.addSpace("group") { space=>
    space ++= GroupDAO
  }
  lazy val context = config.createPersistenceContext

//  def main(args: Array[String]): Unit = {
//    context.transaction { implicit session =>
//      for (p <- GroupDAO.findAllWithExclude(2)) println(p.toString)
//    }
//  }
}
