package tendcloud.tianye.userAthuc.service

import tendcloud.tianye.userAthuc.dao.MainDAO
import tendcloud.tianye.userAthuc.entity.Group

/**
  * Created by tend on 2017/3/5.
  */
class GroupService {
  def createGroup(group: Group): Unit = {
    MainDAO.createGroup(group)
  }

  def updateGroup(group: Group): Unit = {
    MainDAO.updateGroup(group)
  }

  def deleteGroup(group: Group): Unit = {
    MainDAO.deleteGroup(group)
  }

  def findOne(id: Long): Option[Group] = {
    MainDAO.findOneGroup(id)
  }

  def findAll(): Seq[Group] = {
    MainDAO.findAllGroup()
  }

  def findAllWithExclude(excludeGroup: Group): Seq[Group] = {
    MainDAO.findAllWithExclude(excludeGroup)
  }

//  def move(source: Group, target: Group): Unit = {
//
//  }

}
