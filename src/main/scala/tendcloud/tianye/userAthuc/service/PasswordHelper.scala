package tendcloud.tianye.userAthuc.service

import org.apache.shiro.crypto.SecureRandomNumberGenerator
import org.apache.shiro.crypto.hash.SimpleHash
import org.apache.shiro.util.ByteSource
import tendcloud.tianye.userAthuc.entity.User

/**
  * Created by tend on 2017/3/4.
  */
class PasswordHelper {
  val randomNumberGenerator = new SecureRandomNumberGenerator
  val algorithmName: String = "md5"
  val hashIterations: Int = 2

  def encryptPassword(user: User): Unit = {
    user.salt = randomNumberGenerator.nextBytes().toHex
    val newPassword = new SimpleHash(
      algorithmName,
      user.password,
      ByteSource.Util.bytes(user.getCredentialSalt()),
      hashIterations
    ).toHex
    user.password = newPassword
  }
}
