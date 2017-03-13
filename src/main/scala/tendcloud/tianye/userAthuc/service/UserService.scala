package tendcloud.tianye.userAthuc.service

import tendcloud.tianye.userAthuc.dao.MainDAO
import tendcloud.tianye.userAthuc.entity.{SimpleUser, User}

/**
  * Created by tend on 2017/3/4.
  */
class UserService {
  lazy val passwordHelper = new PasswordHelper
  lazy val roleService = new RoleService

  def createUser(user: User): Unit = {
    passwordHelper.encryptPassword(user)
    if (user.roleIds == null ) {
      user.roleIds = List()
      user.locked = false
    }
    MainDAO.createUser(user)
  }

  def updateUser(user: User): Unit = {
    passwordHelper.encryptPassword(user)
    MainDAO.updateUser(user)
  }

  def deleteUser(user: User): Unit = {
    MainDAO.deleteUser(user)
  }

  def changePassword(id: Long, newPassword: String): Unit = {
    val user = MainDAO.findOneUser(id)
    if (user.nonEmpty) {
      user.get.password = newPassword
//      println("1.userService: beforeChange"+user.get.toString)
      passwordHelper.encryptPassword(user.get)
      user.get.setRoleIds()
      MainDAO.updateUser(user.get)
//      println("1.userService: Changed"+user.get.toString)
    }
  }

  def findOne(id: Long): User = {
    MainDAO.findOneUser(id).get
  }

  def findAll(): Seq[User] = {
    MainDAO.findAllUser()
  }

  def findByGroupId(groupId: Long): Seq[SimpleUser] = {
    MainDAO.findByGroupId(groupId)
  }

  def findByUsername(name: String): Option[User] = {
    val user = MainDAO.findByUsername(name)
//    println("query at findByUsername")
    if (user.isDefined) {
      if (user.get.roleIds == null) user.get.setRoleIds()
    }
    user
  }

  def findRoles(username: String): Set[String] = {
    val user = findByUsername(username)
//    if (user.get.getRoleIds().isEmpty)  println("FindRoles: empty")
//    else for (p <- user.get.getRoleIds()) println("FindRoles:"+p.toString)
    if (user.isEmpty) {
      Set.empty
    }else {
      roleService.findRoles(user.get.getRoleIds())
    }
  }

  def findPermission(username: String): Set[String] = {
    val user = findByUsername(username)
    if (user.isEmpty) {
      Set.empty
    }else {
      roleService.findPermissions(user.get.roleIds.toArray)
    }
  }

}
