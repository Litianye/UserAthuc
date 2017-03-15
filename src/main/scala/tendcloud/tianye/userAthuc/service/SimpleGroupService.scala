package tendcloud.tianye.userAthuc.service

import tendcloud.tianye.userAthuc.dao.MainDAO
import tendcloud.tianye.userAthuc.entity.SimpleGroup

/**
  * Created by tend on 2017/3/14.
  */
class SimpleGroupService {
  def createGroup(group: SimpleGroup): Unit = {
    MainDAO.createSimpleGroup(group)
  }

  def updateGroup(group: SimpleGroup): Unit = {
    MainDAO.updateSimpleGroup(group)
  }

  def deleteGroup(group: SimpleGroup): Unit = {
    MainDAO.deleteSimpleGroup(group)
  }

  def findOne(id: Long): Option[SimpleGroup] = {
    MainDAO.findOneSimpleGroup(id)
  }

  def findAll(): Seq[SimpleGroup] = {
    MainDAO.findAllSimpleGroup()
  }

  def findAllWithExclude(excludeGroup: SimpleGroup): Seq[SimpleGroup] = {
    MainDAO.findAllWithSimpleExclude(excludeGroup)
  }
}
