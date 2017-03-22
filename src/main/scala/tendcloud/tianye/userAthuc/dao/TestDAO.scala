package tendcloud.tianye.userAthuc.dao

import org.mybatis.scala.config.Configuration
import tendcloud.tianye.userAthuc.entity.User

/**
  * Created by tend on 2017/3/5.
  */
object TestDAO {
//  val config = Configuration("mybatis.xml")
//  config.addSpace("group") { space=>
//    space ++= UserDAO
//  }
//  lazy val context = config.createPersistenceContext

  def main(args: Array[String]): Unit = {
    println(MainDAO.findOneRole(1))
    println(MainDAO.findOneUser(1))
    println(MainDAO.findOneSimpleGroup(1))
    println(MainDAO.findOneSimpleResource(1))
  //    context.transaction { implicit session =>
  //      UserDAO.updateUser(new User(19, 2, "chi", "2333", "2333", "4,", List(4), false))
  //    }
  }
}
