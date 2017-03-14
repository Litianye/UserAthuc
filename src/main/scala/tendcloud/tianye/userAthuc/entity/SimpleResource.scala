package tendcloud.tianye.userAthuc.entity

/**
  * Created by tend on 2017/3/13.
  */
class SimpleResource extends Serializable {
  var id: Long =_
  var name: String =_
  var permission: String =_
  var available: Boolean =_

  def this(id: Long, name: String, permission: String, available: Boolean){
    this()
    this.name = name
    this.id = id
    this.permission = permission
    this.available = available
  }
}
