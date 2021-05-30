package pro.dengyi.myhome.myhomeutil;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pro.dengyi.myhome.myhomeutil.holder.UserHolderModel;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * jwt工具类
 *
 * @author dengy
 */
public class UserTokenUtil {

    /**
     * subject
     */
    public static final String SUBJECT = "myhome";
    /**
     * 过期时间
     */
    public static final long EXPIREDAYS = 30;
    /**
     * 加密秘钥
     */
    public static final String APPSECRET = "myhome123!@#";

    /**
     * 生成 jwt token方法
     *
     * @return token字符串
     */
    public static String genToken(UserHolderModel userHolderModel) {

        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("userId", userHolderModel.getId())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDate.now().plusDays(EXPIREDAYS).atStartOfDay(ZoneOffset.ofHours(8)).toInstant()))
                .signWith(SignatureAlgorithm.HS256, APPSECRET).compact();
        return token;
    }


    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public static UserHolderModel decToken(String token) {

        UserHolderModel user = new UserHolderModel();
        Claims body = Jwts.parser()
                .setSigningKey(APPSECRET)
                .parseClaimsJws(token)
                .getBody();
        String id = (String) body.get("userId");
        user.setId(id);
        return user;

    }

}
