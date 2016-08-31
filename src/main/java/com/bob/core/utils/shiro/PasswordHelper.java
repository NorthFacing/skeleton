package com.bob.core.utils.shiro;

import com.bob.core.contants.Constants;
import com.bob.modules.sysUser.entity.SysUser;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {
  private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
  public static final String ENCRYPT = Constants.PROJECT_NAME;
  private String algorithmName = "md5";
  private int hashIterations = 2;

  public void encryptPassword(SysUser entity) {
    String salt = randomNumberGenerator.nextBytes().toHex();
    entity.setSalt(salt);
    String newPassword = new SimpleHash(algorithmName,
            entity.getPassWord(),
            ByteSource.Util.bytes(ENCRYPT + entity.getUserName() + salt),
            hashIterations).toHex();
    entity.setPassWord(newPassword);
  }

  public static void main(String[] args) {
    PasswordHelper passwordHelper = new PasswordHelper();
    SysUser entity = new SysUser();
    entity.setUserName("admin");
    // "21232f297a57a5a743894a0e4a801fc3" 是 "admin"使用js的md5加密之后的值：
    entity.setPassWord("21232f297a57a5a743894a0e4a801fc3");
    passwordHelper.encryptPassword(entity);
    System.out.println("salt:\n" + entity.getSalt());
    System.out.println("password:\n" + entity.getPassWord());
  }
}
