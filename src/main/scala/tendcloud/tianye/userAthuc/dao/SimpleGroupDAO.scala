package tendcloud.tianye.userAthuc.dao

import org.mybatis.scala.mapping.{Delete, Insert, JdbcGeneratedKey, ResultMap, SelectList, SelectListBy, SelectOneBy, Update}
import tendcloud.tianye.userAthuc.entity.SimpleGroup

/**
  * Created by tend on 2017/3/14.
  */
object SimpleGroupDAO {
  val createGroup = new Insert[SimpleGroup]() {
    keyGenerator = new JdbcGeneratedKey(null, "id")

    def xsql =
      <xsql>
        INSERT INTO sys_group (name, available)
        VALUES (#{{name}}, #{{available}})
      </xsql>
  }

  val updateGroup = new Update[SimpleGroup] {
    def xsql =
      <xsql>
        UPDATE sys_group
        SET name = #{{name}}, available = #{{available}}
        WHERE id = #{{id}}
      </xsql>
  }

  val deleteSelfGroup = new Delete[SimpleGroup] {
    def xsql =
      <xsql>
        DELETE FROM sys_group WHERE id = #{{id}}
      </xsql>
  }

  val deleteChildGroup = new Delete[SimpleGroup] {
    def xsql =
      <xsql>
        Delete FROM sys_group WHERE parent_ids LIKE #{{id}}
      </xsql>
  }

  val findOne = new SelectOneBy[Long, SimpleGroup] {
    def xsql =
      <xsql>
        SELECT id, name, available
        FROM sys_group
        WHERE id = #{{id}}
      </xsql>
  }

  val findAll = new SelectList[SimpleGroup] {
    resultMap = new ResultMap[SimpleGroup] {
      id(property = "id", column = "id")
      result(property = "name", column = "name")
      result(property = "available", column = "available")
    }

    def xsql =
      <xsql>
        SELECT id, name, available
        FROM sys_group
      </xsql>
  }

  val  findAllWithExclude = new SelectListBy[Long, SimpleGroup]{
    resultMap = new ResultMap[SimpleGroup] {
      id(property = "id", column = "id")
      result(property = "name", column = "name")
      result(property = "available", column = "available")
    }

    def xsql =
      <xsql>
        SELECT id, name, available
        FROM sys_group
        where id != #{{id}} AND parent_ids NOT LIKE #{{'%id%'}}
      </xsql>
  }

  def bind = Seq(createGroup, updateGroup, deleteSelfGroup, deleteChildGroup, findAll, findOne, findAllWithExclude)

}
