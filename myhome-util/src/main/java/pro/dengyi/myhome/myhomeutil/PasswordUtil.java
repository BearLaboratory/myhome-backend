package pro.dengyi.myhome.myhomeutil;

import org.mindrot.jbcrypt.BCrypt;

/**
 * 密码工具类，使用bcrypt非对称加密
 *
 * @author dengy
 */
public class PasswordUtil {

    /**
     * 加密密码
     *
     * @param rawPassword 原始密码
     * @return 加密后密码
     */
    public static String encodePassword(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    /**
     * 校验密码是否相同
     *
     * @param rawPassword    原始密码
     * @param encodePassword 加密后的密码
     * @return 校验状态
     */
    public static Boolean match(String rawPassword, String encodePassword) {
        return BCrypt.checkpw(rawPassword, encodePassword);
    }

    public static void main(String[] args) {
        String encodePassword = encodePassword("12345678");
        System.out.println(encodePassword);
    }
}
