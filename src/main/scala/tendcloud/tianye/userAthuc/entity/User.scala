package tendcloud.tianye.userAthuc.entity

/**
  * Created by tend on 2017/2/26.
  */
class User extends Serializable{
  var id: Long =_
  var group_id: Long =_
  var username: String =_
  var password: String =_
  var salt: String =_
  var roleIds: List[Long] = _
  var roleIdsStr: String = _
  var locked: Boolean = _

  def this(username: String, password: String){
    this()
    this.username = username
    this.password = password
  }

  def this(id: Long) {
    this()
    this.id = id
  }

  def this(groupId: Long, username: String, password: String){
    this(username, password)
    this.group_id = groupId
  }

  def this(group_id: Long, username: String, password: String, roleIds: List[Long]){
    this(username, password)
    this.group_id = group_id
    this.roleIds = roleIds
    this.roleIdsStr = getRoleIdsStr()
  }

  def this(id: Long, group_id: Long, username: String, password: String, roleIds: List[Long]){
    this(username, password)
    this.id = id
    this.group_id = group_id
    this.roleIds = roleIds
    this.roleIdsStr = getRoleIdsStr()
  }

  def getCredentialSalt(): String = {
    username+salt
  }

  def getRoleIdsStr(): String = {
    if (roleIds.isEmpty) ""
    else {
      val s = new StringBuilder
      for (roleId <- roleIds) s.append(roleId+",")
      s.toString()
    }
  }
//
//  def setRoleIdsStr(roleIdsStr: String): Unit = {
//    if (roleIdsStr.isEmpty) return
//    val roleIdStrs: Array[String] = roleIdsStr.split(",")
//    for (roleIdStr <- roleIdStrs) {
//      if (roleIdStr.isEmpty) {
//        ;
//      }
//      roleIdStr.asInstanceOf[Long] :: roleIds
//    }
//  }
//
//  override def equals(obj: scala.Any): Boolean = {
//    if (this == obj) true
//    if (obj == null || getClass != obj.getClass) false
//
//    val user = obj.asInstanceOf[User]
//    if (if (id != null) !id.equals(user.id) else user.id != null) false
//    else true
//  }
//
//  override def hashCode(): Int = {
//    if (id != null) id.hashCode() else 0
//  }
//
  override def toString: String = {
    "User{ id="+ id +", groupId="+ group_id +", username='"+ username +'\''+
      ", password='"+ password +'\''+", salt='"+ salt +'\''+", roleIds="+ roleIdsStr +
      ", locked="+ locked +'}'
  }
}
