package tendcloud.tianye.userAthuc.entity

/**
  * Created by tend on 2017/3/14.
  */
class SimpleGroup extends Serializable{
  var id: Long = _
  var name: String = _
  var available: Boolean = _

  def this (name: String, available: Boolean) {
    this()
    this.name = name
    this.available = available
  }

}
