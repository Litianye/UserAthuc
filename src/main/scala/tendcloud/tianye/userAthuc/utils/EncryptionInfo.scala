package tendcloud.tianye.userAthuc.utils

import javax.crypto.spec.SecretKeySpec
import javax.crypto.{Mac, SecretKey}

import org.apache.shiro.codec.Hex

/**
  * Created by tend on 2017/3/13.
  */
object EncryptionInfo {
  def digest(key: String, content: String): String = {
    try {
      val mac: Mac  = Mac.getInstance("HmacSHA256")
      val secretByte = key.getBytes("utf-8")
      val dataBytes = content.getBytes("utf-8")

      val secret: SecretKey = new SecretKeySpec(secretByte, "HmacSHA256")
      mac.init(secret)

      val doFinal = mac.doFinal(dataBytes)
      val hexB = Hex.encode(doFinal)
      hexB.toString
    }catch {
      case rex: RuntimeException => {
        println(rex.toString)
        "rex"
      }
    }
  }

  def digest(key: String, map: Map[String, _]): String = {
    val s: StringBuilder = new StringBuilder()
    for (values <- map.values) {
      values match {
        case arrays: Array[_] =>
          for (value <- arrays.asInstanceOf[Array[String]]) {
            s.append(value)
          }
        case list: List[_] =>
          for (value <- list.asInstanceOf[List[String]]) {
            s.append(value)
          }
        case _ => s.append(values)
      }
    }
    digest(key, s.toString())
  }
}
