package tendcloud.tianye.userAthuc.dao

import org.mybatis.scala.mapping.{Delete, Insert, JdbcGeneratedKey, ResultMap, SelectList, SelectListBy, SelectOne, SelectOneBy, Update}
import tendcloud.tianye.userAthuc.entity.{Group, Resource}

/**
  * Created by tend on 2017/3/4.
  */
object GroupDAO {

  val createGroup = new Insert[Group]() {
    keyGenerator = new JdbcGeneratedKey(null, "id")

    def xsql =
      <xsql>
        INSERT INTO sys_group (name, parent_id, parent_ids, available)
        VALUES (#{{name}}, #{{parentId}}, #{{parentIds}}, #{{available}})
      </xsql>
  }

  val updateGroup = new Update[Group] {
    def xsql =
      <xsql>
        UPDATE sys_group
        SET name = #{{name}}, parent_id = #{{parentId}},
        parent_ids = #{{parentIds}}, available = #{{available}}
        WHERE id = #{{id}}
      </xsql>
  }

  val deleteSelfGroup = new Delete[Group] {
    def xsql =
      <xsql>
        DELETE FROM sys_group WHERE id = #{{id}}
      </xsql>
  }

  val deleteChildGroup = new Delete[Group] {
    def xsql =
      <xsql>
        Delete FROM sys_group WHERE parent_ids LIKE #{{id}}
      </xsql>
  }

  val findOne = new SelectOneBy[Long, Group] {
    def xsql =
      <xsql>
        SELECT id, name, parent_id, parent_ids, available
        FROM sys_group
        WHERE id = #{{id}}
      </xsql>
  }

  val findAll = new SelectList[Group] {
    resultMap = new ResultMap[Group] {
      id(property = "id", column = "id")
      result(property = "name", column = "name")
      result(property = "parentId", column = "parent_id")
      result(property = "parentIds", column = "parent_ids")
      result(property = "available", column = "available")
    }

    def xsql =
      <xsql>
        SELECT id, name, parent_id, parent_ids, available
        FROM sys_group
      </xsql>
  }

  val  findAllWithExclude = new SelectListBy[Long, Group]{
    resultMap = new ResultMap[Group] {
      id(property = "id", column = "id")
      result(property = "name", column = "name")
      result(property = "parentId", column = "parent_id")
      result(property = "parentIds", column = "parent_ids")
      result(property = "available", column = "available")
    }

    def xsql =
      <xsql>
        SELECT id, name, parent_id, parent_ids, available
        FROM sys_group
        where id != #{{id}} AND parent_ids NOT LIKE #{{'%id%'}}
      </xsql>
  }

//  val moveSource = new Update[Group] {
//    def xsql =
//      <xsql>
//        UPDATE sys_group
//        SET parent_id = #{{parentId}} ,parent_ids = #{{parentIds}}
//        where id = #{{id}}
//      </xsql>
//  }
//
//  val moveSourceDescendants = new Update[Group] {
//    def xsql =
//      <xsql>
//        UPDATE sys_group
//        SET parent_ids = concat(?, substring(parent_ids, length(?)))
//        WHERE parent_ids like ?
//      </xsql>
//  }

  def bind = Seq(createGroup, updateGroup, deleteSelfGroup, deleteChildGroup, findAll, findOne, findAllWithExclude)

}
