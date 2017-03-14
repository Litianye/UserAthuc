package tendcloud.tianye.userAthuc.service

import org.apache.shiro.authz.permission.WildcardPermission
import tendcloud.tianye.userAthuc.dao.MainDAO
import tendcloud.tianye.userAthuc.entity.{Resource, ResourceType}

/**
  * Created by tend on 2017/3/5.
  */
class ResourceService {

  def createResource(resource: Resource): Unit = {
    MainDAO.createResource(resource)
  }

  def updateResource(resource: Resource): Unit = {
    MainDAO.updateResource(resource)
  }

  def deleteResource(resource: Resource): Unit = {
    MainDAO.deleteResource(resource)
  }

  def findOne(id: Long): Option[Resource] = {
    MainDAO.findOneResource(id)
  }

  def findAll(): Seq[Resource] = {
    MainDAO.findAllResource()
  }

  def findPermissions(resourceIds: Set[Long]): Set[String] = {
    val permissions = collection.mutable.Set[String]()
    for (resourceId <- resourceIds) {
      val resource = findOne(resourceId)
      if (resource != null && resource.get.permission.nonEmpty) {
        permissions.add(resource.get.permission)
      }
    }
    permissions.toSet
  }

  def findPermissionsTest(resourceIds: Set[Long]): Set[String] = {
    val permissions = collection.mutable.Set[String]()
    for (resourceId <- resourceIds) {
      val resource = findOne(resourceId)
      if (resource != null && resource.get.permission.nonEmpty) {
        permissions.add(resource.get.permission)
      }
    }
    permissions.toSet
  }

  def findMenus(permissions: Set[String]): Seq[Resource] = {
    val allResources = findAll()
    val menus = Seq[Resource]()
    for (resource <- allResources) {
      if (!resource.isRootNode) {
        if (resource.resourceType == ResourceType.menu) {
          if (hasPermission(permissions, resource)) {
            menus :+ resource
          }
        }
      }
    }
    menus
  }

  def hasPermission(permissions: Set[String], resource: Resource): Boolean = {
    if (resource.permission.isEmpty) return true
    for (permission <- permissions) {
      val p1 = new WildcardPermission(permission)
      val p2 = new WildcardPermission(resource.permission)
      if (p1.implies(p2) || p2.implies(p1)) return true
    }
    false
  }
}
