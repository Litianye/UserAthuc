package tendcloud.tianye.userAthuc.dao

import org.mybatis.scala.mapping.{Delete, Insert, JdbcGeneratedKey, ResultMap, SelectList, SelectListBy, SelectOneBy, Update}
import tendcloud.tianye.userAthuc.entity.{SimpleUser, User}

/**
  * Created by tend on 2017/3/3.
  */
object UserDAO {
  //UserImpl
  val createUser = new Insert[User] {
    keyGenerator = JdbcGeneratedKey(null, "id")
    def xsql =
      <xsql>
        INSERT INTO sys_user (group_id, username, password, salt, role_ids, locked)
        VALUES (#{{group_id}}, #{{username}}, #{{password}}, #{{salt}}, #{{roleIdsStr}}, #{{locked}})
      </xsql>
  }

  val updateUser = new Update[User] {
    def xsql =
      <xsql>
        UPDATE sys_user
        SET group_id = #{{group_id}}, username = #{{username}}, password = #{{password}},
        salt = #{{salt}}, role_ids = #{{roleIdsStr}}, locked = #{{locked}}
        WHERE id = #{{id}}
      </xsql>
  }

  val deleteUser = new Delete[User] {
    def xsql =
      <xsql>
        DELETE FROM sys_user WHERE id= #{{id}}
      </xsql>
  }

  val findOne = new SelectOneBy[Long, User] {
    def xsql =
      <xsql>
        SELECT id, group_id, username, password, salt, role_ids as roleIdsStr, locked
        FROM sys_user
        WHERE id = #{{id}}
      </xsql>
//    println("UserDAO findOne.")
  }

  val findAll = new SelectList[User] {
    // Define the result mapping
    resultMap = new ResultMap[User] {
      id(property = "id", column = "id")
      result(property = "group_id", column = "group_id")
      result(property = "username", column = "username")
      result(property = "roleIdsStr", column = "role_ids")
      result(property = "locked", column = "locked")
    }

    def xsql =
      <xsql>
        SELECT id, group_id, username, role_ids as roleIdsStr, locked
        FROM sys_user
      </xsql>
  }

  val findByUsername = new SelectOneBy[String, User] {
    def xsql =
      <xsql>
        SELECT id, group_id, username, password, salt, role_ids as roleIdsStr, locked
        FROM sys_user
        WHERE username = #{{username}}
      </xsql>
  }

  val findByGroupId = new SelectListBy[Long , SimpleUser] {
    def xsql =
      <xsql>
        SELECT group_id, username, role_ids as roleIdsStr, locked
        FROM sys_user
        WHERE group_id = #{{group_id}}
      </xsql>
  }

  def bind = Seq(createUser, updateUser, deleteUser, findOne, findAll, findByUsername, findByGroupId)
}
