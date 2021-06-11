package pro.dengyi.myhome.common.response;

/**
 * 统一响应枚举类
 * 枚举类划分依据：基础公共以10开头，其他根据微服务划分code一次递增过去就行了
 * <p>
 * 在最终与前端对接时，请将特殊的code列表给前端，让前端进行针对性的判断提示
 *
 * @author BLab
 */
public enum ResponseEnum {
    /**
     * ------------------------基础通用响应枚举----------------------------------
     */
    SUCCESS(true, 10000, "操作成功"),
    NEED_LOGIN(false, 10001, "此操作需要登录后才能继续"),
    TOKEN_ERROR(false, 10002, "Token异常，请重新登录！"),
    TOKEN_EXPIRE(false, 10003, "Toke过期，请重新登录！"),
    FLOW_LIMIT(false, 10004, "服务调用流量控制，请稍后重试！"),
    SERVICE_CALL_ERROR(false, 10005, "服务调用异常，请稍后重试！"),
    SERVICE_NOT_RUNNING(false, 10006, "目标服务未启动，请稍后重试！"),
    SERVICE_INNER_CALL_NOT_RUNNING(false, 10007, "服务内部调用时，目标服务未启动"),
    PARAM_ERROR(false, 11111, "接口调用参数异常"),
    SYSTEM_ERROR(false, 99999, "系统开小差了"),

    /**
     * ------------------------用户中心微服务11-----------------------
     */
    USER_EXIST(false, 11001, "用户已存在"),
    NO_PERMISSION(false, 11002, "权限不足"),
    ADMIN_CAN_NOT_DEL(false, 11003, "超级管理员不能删除"),
    USER_NOT_EXIST_OR_PASS_ERROR(false, 11004, "用户不存在或密码错误"),
    CODE_EXPIRE(false, 11005, "验证码已过期"),
    CODE_ERROR(false, 11006, "验证码错误"),
    CODE_FLOW_LIMIT(false, 11007, "请求验证码太频繁，请稍后重试"),
    HOSPITAL_EXIST(false, 11008, "bdId已存在，请检查后重新保存"),
    ROLE_USED(false, 11009, "角色已被使用不能删除"),


    /**
     * ----------------------维护系统微服务12------------------------
     */
    //DEVICE_NOT_ONLINE(false, 12001, "设备已离线"),
    OPT_FLOW_LIMIT(false, 12002, "对设备操作太频繁，请稍后重试"),


    /**
     * -----------------------大屏微服务13---------------------------
     */
    APK_EXIST(false, 13001, "APK已存在"),


    /**
     * --------------------------家庭、楼层、房间-----------
     */
    HAVA_SUB_ROOMS(false, 14001, "楼层下还存在着房间，无法删除"),
    ROOM_CONTAIN_DEVICE(false, 14002, "房间下还存在设备，无法删除"),
    /**
     * -------------设备相关------------------
     */
    CATEGORY_USED(false, 30001, "设备分类已被设备绑定，不能删除"),
    DEVICE_TYPE_CAN_NOT_CONTROL(false, 30001, "设备类型不支持下发控制命令"),
    DEVICE_NOT_ONLINE(false, 30002, "设备不在线,不能下发控制命令"),

    ;


    private Boolean status;
    private Integer code;
    private String message;

    /**
     * 构造
     *
     * @param status  状态
     * @param code    编码
     * @param message 消息
     */
    ResponseEnum(Boolean status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
