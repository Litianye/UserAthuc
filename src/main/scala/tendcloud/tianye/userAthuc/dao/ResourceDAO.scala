package tendcloud.tianye.userAthuc.dao

import org.mybatis.scala.mapping.{Delete, Insert, JdbcGeneratedKey, ResultMap, SelectList, SelectOneBy, Update, XSQL}
import tendcloud.tianye.userAthuc.entity.Resource

/**
  * Created by tend on 2017/3/4.
  */
object ResourceDAO {

  val createResource = new Insert[Resource] {
    keyGenerator = new JdbcGeneratedKey(null, "id")
    def xsql =
      <xsql>
        INSERT INTO sys_resource (name, type, url,
                                  parent_id, parent_ids, permission, available)
        VALUES (#{{name}}, #{{resourceType}}, #{{url}},
                #{{parentId}}, #{{parentIds}}, #{{permission}}, #{{available}})
      </xsql>
  }

  val updateResource = new Update[Resource] {
    def xsql =
      <xsql>
        UPDATE sys_resource
        SET name = #{{name}}, type = #{{type}}, url = #{{url}}, parent_id = #{{parentId}}
        parent_ids = #{{parentIds}}, permission = #{{permission}}, available = #{{available}}
        WHERE id = #{{id}}
      </xsql>
  }

  val deleteSelfResource = new Delete[Resource] {
    def xsql =
      <xsql>
        DELETE FROM sys_resource WHERE id = #{{id}}
      </xsql>
  }

  val deleteChildResource = new Delete[Resource] {
    def xsql =
      <xsql>
        DELETE FROM sys_resource WHERE parent_ids LIKE #{{id}}
      </xsql>
  }

  val findOne = new SelectOneBy[Long, Resource] {
    def xsql =
      <xsql>
        SELECT id, name, type, url,
        parent_id as parentId, parent_ids as parentIds, permission, available
        FROM sys_resource
        WHERE id = #{{id}}
      </xsql>
  }

  val findOneTest = new SelectOneBy[Long, Resource] {
    def xsql =
      <xsql>
        SELECT id, name, permission, available
        FROM sys_resource
        WHERE id = #{{id}}
      </xsql>
  }

  val findAll = new SelectList[Resource] {
    //mapping
    resultMap = new ResultMap[Resource] {
      id(property = "id", column = "id")
      result(property = "name", column = "name")
      result(property = "type", column = "type")
      result(property = "url", column = "url")
      result(property = "parentId", column = "parent_id")
      result(property = "parentIds", column = "parent_ids")
      result(property = "permission", column = "permission")
      result(property = "available", column = "available")
    }

    def xsql =
      <xsql>
        SELECT id, name, type, url, parent_id, parent_ids, permission, available
        FROM sys_resource
      </xsql>
  }

  def bind = Seq(createResource, updateResource, deleteSelfResource, deleteChildResource, findOne, findAll, findOneTest)
}

