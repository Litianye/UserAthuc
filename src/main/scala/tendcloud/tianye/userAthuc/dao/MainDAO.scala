package tendcloud.tianye.userAthuc.dao

import org.mybatis.scala.config.Configuration
import tendcloud.tianye.userAthuc.entity._

/**
  * Created by tend on 2017/3/4.
  */
object MainDAO {
  val config = Configuration("mybatis.xml")

  config.addSpace("user") { space=>
    space ++= UserDAO
  }
  config.addSpace("role") { space=>
    space ++= RoleDAO
  }
  config.addSpace("resource") { space=>
    space ++= ResourceDAO
  }
  config.addSpace("group") { space=>
    space ++= GroupDAO
  }
  val context = config.createPersistenceContext

  def createUser(user: User): Unit = {
    context.transaction { implicit session =>
      UserDAO.createUser(user)
    }
  }

  def updateUser(user: User): Unit = {
    context.transaction { implicit session =>
      UserDAO.updateUser(user)
    }
  }

  def deleteUser(user: User): Unit = {
    context.transaction { implicit session =>
      UserDAO.deleteUser(user)
    }
  }

  def findOneUser(id: Long): Option[User] = {
    context.transaction { implicit session =>
      UserDAO.findOne(id)
    }
  }

  def findAllUser(): Seq[User] = {
    context.transaction { implicit session =>
      UserDAO.findAll()
    }
  }

  def findByUsername(name: String): Option[User] = {
    context.transaction { implicit session =>
      UserDAO.findByUsername(name)
    }
  }

  def findByGroupId(groupId: Long): Seq[SimpleUser] = {
    context.transaction { implicit session =>
      UserDAO.findByGroupId(groupId)
    }
  }

  def createRole(role: Role): Unit = {
    context.transaction { implicit session =>
      RoleDAO.createRole(role)
    }
  }

  def updateRole(role: Role): Unit = {
    context.transaction { implicit session =>
      RoleDAO.updateRole(role)
    }
  }

  def deleteRole(role: Role): Unit = {
    context.transaction { implicit session =>
      RoleDAO.deleteRole(role)
    }
  }

  def findOneRole(id: Long): Option[Role] = {
    context.transaction { implicit session =>
      RoleDAO.findOne(id)
    }
  }

  def findAllRole(): Seq[Role] = {
    context.transaction { implicit session =>
      RoleDAO.findAll()
    }
  }

  def createResource(resource: Resource): Unit = {
    context.transaction { implicit session =>
      ResourceDAO.createResource(resource)
    }
  }

  def updateResource(resource: Resource): Unit = {
    context.transaction { implicit session =>
      ResourceDAO.updateResource(resource)
    }
  }

  def deleteResource(resource: Resource): Unit = {
    context.transaction { implicit session =>
      ResourceDAO.deleteSelfResource(resource)
      ResourceDAO.deleteChildResource(resource)
    }
  }

  def findOneResource(id: Long): Option[Resource] = {
    context.transaction { implicit session =>
      ResourceDAO.findOne(id)
    }
  }

  def findOneResourceTest(id: Long): Option[Resource] = {
    context.transaction { implicit session =>
      ResourceDAO.findOneTest(id)
    }
  }

  def findAllResource(): Seq[Resource] = {
    context.transaction { implicit session =>
      ResourceDAO.findAll()
    }
  }

  def createGroup(group: Group): Unit = {
    context.transaction { implicit session =>
      GroupDAO.createGroup(group)
//      val adminRole = new Role(group.name+"Admin", group.name+"管理员", List(11,21,31,41), true)
//      val userRole = new Role(group.name+"User", group.name+"用户", List(11,21), true)
//      RoleDAO.createRole(adminRole)
//      RoleDAO.createRole(userRole)
    }
  }

  def updateGroup(group: Group): Unit = {
    context.transaction { implicit session =>
      GroupDAO.updateGroup(group)
    }
  }

  def deleteGroup(group: Group): Unit = {
    context.transaction { implicit session =>
      GroupDAO.deleteSelfGroup(group)
      GroupDAO.deleteChildGroup(group)
    }
  }

  def findOneGroup(id: Long): Option[Group] = {
    context.transaction { implicit session =>
      GroupDAO.findOne(id)
    }
  }

  def findAllGroup(): Seq[Group] = {
    context.transaction { implicit session =>
      GroupDAO.findAll()
    }
  }

  def findAllWithExclude(group: Group): Seq[Group] = {
    context.transaction { implicit session =>
      GroupDAO.findAllWithExclude(group.id)
    }
  }
}
