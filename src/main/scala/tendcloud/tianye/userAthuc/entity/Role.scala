package tendcloud.tianye.userAthuc.entity


/**
  * Created by tend on 2017/2/26.
  */
class Role extends Serializable{
  var id: Long =_
  var role: String =_
  var description: String =_
  var resourceIds: List[Long] =_
  var resourceIdsStr: String = _
  var available: Boolean =_

  def this(role: String, description: String, resourceIds: List[Long], available: Boolean) {
    this()
    this.role = role
    this.description = description
    this.resourceIds = resourceIds
    this.available = available
    this.resourceIdsStr = getResourceIdsStr()
  }

  def this(id: Long) {
    this()
    this.id = id
  }

  def this(id: Long, role: String, description: String, resourceIds: List[Long], available: Boolean) {
    this(role, description, resourceIds, available)
    this.id = id
  }

  def getResourceIdsStr(): String = {
    if (resourceIds.isEmpty) ""
    else {
      val s = new StringBuilder
      for (resourceId <- resourceIds) s.append(resourceId+",")
      s.toString()
    }
  }

  def getResourceIds(): List[Long] = {
    if (resourceIds == null) List() else resourceIds
  }

  def setResourceIds(): Unit = {
    if (resourceIdsStr == null) resourceIds = List()
    else {
      val temp = resourceIdsStr.split(",").toList
      resourceIds = temp.map(f => f.toLong)
    }
  }

//  def setResourceIdsStr(resourceIdsStr: String): Unit = {
//    if (resourceIdsStr.isEmpty) return
//    val resourceIdStrs: Array[String] = resourceIdsStr.split(",")
//    for (resourceIdStr <- resourceIdStrs) {
//      if (resourceIdStr.isEmpty) {
//        ;
//      }
//      resourceIds.add(resourceIdStr.asInstanceOf[Long])
//    }
//  }
//
//  override def equals(obj: scala.Any): Boolean = {
//    if (this == obj) true
//    if (obj == null || getClass != obj.getClass) false
//
//    val role = obj.asInstanceOf[Role]
//    if (if (id != null) !id.equals(role.id) else role.id != null) false
//    else true
//  }
//
//  override def hashCode(): Int = {
//    if (id != null) id.hashCode() else 0
//  }
//
  override def toString: String = {
    "Role{ id="+ id +", role='"+ role + '\''+", description='"+ description +'\''+
      ", resourceIds="+ resourceIdsStr +", available="+ available +'}'
  }
}
