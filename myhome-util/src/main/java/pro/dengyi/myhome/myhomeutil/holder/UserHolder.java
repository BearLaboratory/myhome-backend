package pro.dengyi.myhome.myhomeutil.holder;

/**
 * @author DengYi
 * @version v1.0
 */
public class UserHolder {

    private static final ThreadLocal<UserHolderModel> LOCAL = new ThreadLocal<>();

    /**
     * 获取当前用户的用户id
     *
     * @return 用户id
     */
    public static String getUserId() {
        return LOCAL.get().getId();
    }

    /**
     * 获取用户完整信息
     * 注意：暂时不用，项目中不需要
     *
     * @return 用户信息实体
     */
    public static UserHolderModel getUser() {
        return LOCAL.get();
    }

    /**
     * 设置用户信息
     *
     * @param userHolderModel 用户信息
     */
    public static void setUser(UserHolderModel userHolderModel) {
        LOCAL.set(userHolderModel);
    }

    /**
     * 清空
     */
    public static void remove() {
        LOCAL.remove();
    }
}