package tendcloud.tianye.userAthuc.service

import tendcloud.tianye.userAthuc.dao.MainDAO
import tendcloud.tianye.userAthuc.entity.Role


/**
  * Created by tend on 2017/3/5.
  */
class RoleService {

  lazy val resourceService = new ResourceService

  def createRole(role: Role): Unit = {
    MainDAO.createRole(role)
  }

  def updateRole(role: Role): Unit = {
    MainDAO.updateRole(role)
  }

  def deleteRole(role: Role): Unit = {
    MainDAO.deleteRole(role)
  }

  def findOne(id: Long): Option[Role] = {
    MainDAO.findOneRole(id)
  }

  def findAll(): Seq[Role] = {
    MainDAO.findAllRole()
  }

  def findRoles(ids: List[Long]): Set[String] = {
    val roles = collection.mutable.Set[String]()
    for (id <- ids) {
      val role = findOne(id)
      if (role != null) {
        roles.add(role.get.role)
      }
    }
//    println("2.roles:"+roles)
    roles.toSet
  }

  def findPermissions(roleIds: Array[Long]): Set[String] = {
    val resourceIds = collection.mutable.Set[Long]()
    for (roleId <- roleIds) {
      val role = findOne(roleId)
      if (role.get != null) {
//        println("role.get != null")
//        println("resourceIds:"+role.get.resourceIds)
        role.get.setResourceIds()
//        println("resourceIds:"+role.get.resourceIds)
        for (resourceId <- role.get.resourceIds) {
          resourceIds.add(resourceId)
        }
      }else {
//        println("role.get = null")
      }
    }
    resourceService.findPermissions(resourceIds.toSet)
  }

}
