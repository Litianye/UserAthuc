package tendcloud.tianye.userAthuc.entity

/**
  * Created by tend on 2017/2/24.
  */
class Group extends Serializable{
  var id: Long = _
  var name: String = _
  var parentId: Long = _
  var parentIds: String = _
  var available: Boolean = _

//  def isRootNode(): Boolean = {
//    parentId == 0
//  }
//
//  def makeSelfAsParentIds(): String = {
//    parentId+id+"/"
//  }
//
//  override def equals(obj: scala.Any): Boolean = {
//    if (this == obj) true
//    if (obj == null || getClass != obj.getClass) false
//
//    val that = obj.asInstanceOf[group]
//    if (if (id != null) !id.equals(that.id) else that.id != null) false
//    else true
//  }
//
//  override def hashCode(): Int = {
//    if (id != null) id.hashCode() else 0
//  }
//
  override def toString: String = {
    "Organization{ id="+ id +", name='"+ name +'\''+", parentId="+ parentId +
      ", parentIds='"+ parentIds +'\''+", available="+ available +'}'
  }
}
