package pro.dengyi.myhome.myhomeutil;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pro.dengyi.myhome.myhomeutil.holder.SysUserHolderModel;

import java.util.Date;

/**
 * jwt工具类
 *
 * @author dengy
 */
public class SysUserTokenUtil {

    /**
     * subject
     */
    public static final String SUBJECT = "myhomebackend";
    /**
     * 过期时间
     */
    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    /**
     * 加密秘钥
     */
    public static final String APPSECRET = "myhomebackend123!@#";


    /**
     * 生成 jwt token方法
     *
     * @return token字符串
     */
    public static String genToken(SysUserHolderModel sysUserHolderModel) {
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("userId", sysUserHolderModel.getId())
                .claim("isDeveloper", sysUserHolderModel.getIsDeveloper())
                .claim("isSuperAdmin", sysUserHolderModel.getIsSuperAdmin())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, APPSECRET).compact();
        return token;
    }


    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public static SysUserHolderModel decToken(String token) {

        SysUserHolderModel user = new SysUserHolderModel();
        Claims body = Jwts.parser()
                .setSigningKey(APPSECRET)
                .parseClaimsJws(token)
                .getBody();
        String id = (String) body.get("userId");
        boolean isDeveloper = (boolean) body.get("isDeveloper");
        boolean isSuperAdmin = (boolean) body.get("isSuperAdmin");
        user.setId(id);
        user.setIsDeveloper(isDeveloper);
        user.setIsSuperAdmin(isSuperAdmin);
        return user;

    }

}
