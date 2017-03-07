package tendcloud.tianye.userAthuc.service

import tendcloud.tianye.userAthuc.dao.MainDAO
import tendcloud.tianye.userAthuc.entity.User
import scala.collection.mutable.Set

/**
  * Created by tend on 2017/3/4.
  */
class UserService {
  lazy val passwordHelper = new PasswordHelper
  lazy val roleService = new RoleService

  def createUser(user: User): Unit = {
    passwordHelper.encryptPassword(user)
    if (user.roleIds == null ) {
      user.roleIds = List(2333)
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
      passwordHelper.encryptPassword(user.get)
      MainDAO.updateUser(user.get)
    }
  }

  def findOne(id: Long): User = {
    MainDAO.findOneUser(id).get
  }

  def findAll(): Seq[User] = {
    MainDAO.findAllUser()
  }

  def findByUsername(name: String): User = {
    MainDAO.findByUsername(name).get
  }

  def findRoles(username: String): Set[String] = {
    val user = findByUsername(username)
    if (user == null) {
      collection.mutable.Set.empty[String]
    }else {
      roleService.findRoles(user.roleIds)
    }
  }

  def findPermission(username: String): Set[String] = {
    val user = findByUsername(username)
    if (user == null) {
      collection.mutable.Set.empty[String]
    }else {
      roleService.findPermissions(user.roleIds.toArray)
    }
  }

}
