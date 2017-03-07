package tendcloud.tianye.userAthuc.dao

import org.mybatis.scala.mapping.{Delete, Insert, JdbcGeneratedKey, ResultMap, SelectList, SelectOneBy, Update}
import tendcloud.tianye.userAthuc.entity.{Role, User}

/**
  * Created by tend on 2017/3/3.
  */
object RoleDAO {

  val createRole = new Insert[Role] {
    keyGenerator = JdbcGeneratedKey(null, "id")
    def xsql =
      <xsql>
        INSERT INTO sys_role (role, description, resource_ids, available)
        VALUES (#{{role}}, #{{description}}, #{{resourceIdsStr}}, #{{available}})
      </xsql>
  }

  val updateRole = new Update[Role] {
    def xsql =
      <xsql>
        UPDATE sys_role
        SET role = #{{role}}, description = #{{description}},
            resource_ids = #{{resourceIdsStr}}, available = #{{available}}
        WHERE id = #{{id}}
      </xsql>
  }

  val deleteRole = new Delete[Role] {
    def xsql =
      <xsql>
        DELETE FROM sys_role WHERE id= #{{id}}
      </xsql>
  }

  val findOne = new SelectOneBy[Long, Role] {
    def xsql =
      <xsql>
        SELECT role, description, resource_ids, available
        FROM sys_role
        WHERE id = #{{id}}
      </xsql>
  }

  val findAll = new SelectList[Role] {
    // Define the result mapping
    resultMap = new ResultMap[Role] {
      id(property = "id", column = "id")
      result(property = "role", column = "role")
      result(property = "description", column = "description")
      result(property = "resourceIdsStr", column = "resource_ids")
      result(property = "available", column = "available")
    }

    def xsql =
      <xsql>
        SELECT id, role, description, resource_ids, available
        FROM sys_role
      </xsql>
  }

  def bind = Seq(createRole, updateRole, deleteRole, findOne, findAll)

}
