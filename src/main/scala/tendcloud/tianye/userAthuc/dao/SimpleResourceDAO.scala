package tendcloud.tianye.userAthuc.dao

import org.mybatis.scala.mapping.{Delete, Insert, JdbcGeneratedKey, ResultMap, SelectList, SelectOneBy, Update}
import tendcloud.tianye.userAthuc.entity.SimpleResource

/**
  * Created by tend on 2017/3/14.
  */
object SimpleResourceDAO {
  val createResource = new Insert[SimpleResource] {
    keyGenerator = new JdbcGeneratedKey(null, "id")
    def xsql =
      <xsql>
        INSERT INTO sys_resource (name, permission, available)
        VALUES (#{{name}}, #{{permission}}, #{{available}})
      </xsql>
  }

  val updateResource = new Update[SimpleResource] {
    def xsql =
      <xsql>
        UPDATE sys_resource
        SET name = #{{name}}, permission = #{{permission}}, available = #{{available}}
        WHERE id = #{{id}}
      </xsql>
  }

  val deleteSelfResource = new Delete[SimpleResource] {
    def xsql =
      <xsql>
        DELETE FROM sys_resource WHERE id = #{{id}}
      </xsql>
  }

  val deleteChildResource = new Delete[SimpleResource] {
    def xsql =
      <xsql>
        DELETE FROM sys_resource WHERE parent_ids LIKE #{{id}}
      </xsql>
  }

  val findOne = new SelectOneBy[Long, SimpleResource] {
    def xsql =
      <xsql>
        SELECT id, name, permission, available
        FROM sys_resource
        WHERE id = #{{id}}
      </xsql>
  }

  val findAll = new SelectList[SimpleResource] {
    //mapping
    resultMap = new ResultMap[SimpleResource] {
      id(property = "id", column = "id")
      result(property = "name", column = "name")
      result(property = "permission", column = "permission")
      result(property = "available", column = "available")
    }

    def xsql =
      <xsql>
        SELECT id, name, permission, available
        FROM sys_resource
      </xsql>
  }

  def bind = Seq(createResource, updateResource, deleteSelfResource, deleteChildResource, findOne, findAll)

}
