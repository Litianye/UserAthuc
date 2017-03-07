package tendcloud.tianye.userAthuc.entity

/**
  * Created by tend on 2017/2/26.
  */
class Resource extends Serializable{
  var id: Long =_
  var name: String =_
  var resourceType: ResourceType.Value = _
  var url: String =_
  var permission: String =_
  var parentId: Long =_
  var parentIds: String =_
  var available: Boolean =_

  def this(name: String, resourceType: ResourceType.Value, url: String){
    this()
    this.name = name
    this.resourceType = resourceType
    this.url = url
  }

  def this(name: String, resourceType: ResourceType.Value, url: String,
           permission: String, parentId: Long, parentIds: String, available: Boolean) {
    this(name, resourceType, url)
    this.permission = permission
    this.parentId = parentId
    this.parentIds = parentIds
    this.available = available
  }

  def isRootNode: Boolean = {
    parentId == 0
  }

//  def makeSelfAsParentIds(): String = {
//    parentIds + id + "/"
//  }
//
//  override def equals(obj: scala.Any): Boolean = {
//    if (this == obj) true
//    if (obj == null || getClass != obj.getClass) false
//
//    val resource = obj.asInstanceOf[Resource]
//
//    if (if (id != null) !id.equals(resource.id) else resource.id != null) false
//    else true
//  }
//
//  override def hashCode(): Int = {
//    if (id != null) id.hashCode()
//    else 0
//  }
}

object ResourceType extends Enumeration {
  val menu = Value(0, "菜单")
  val button = Value(1, "按钮")
}
