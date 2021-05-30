package pro.dengyi.myhome.myhomeutil.holder;


/**
 * 用户ThreadLocal
 *
 * @author dengy
 */
public class GatewayTokenHolder {

    private static final ThreadLocal<String> LOCAL = new ThreadLocal<>();

    /**
     * 获取当前用户的用户id
     *
     * @return 用户id
     */
    public static String getToken() {
        return LOCAL.get();
    }


    /**
     * 设置用户信息
     *
     * @param token 用户信息
     */
    public static void setToken(String token) {
        LOCAL.set(token);
    }

    /**
     * 清空
     */
    public static void remove() {
        LOCAL.remove();
    }
}
