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
    DEVICE_NOT_ONLINE(false, 12001, "设备已离线"),
    OPT_FLOW_LIMIT(false, 12002, "对设备操作太频繁，请稍后重试"),


    /**
     * -----------------------大屏微服务13---------------------------
     */
    APK_EXIST(false, 13001, "APK已存在"),

    /**
     * AI_SCREEN 异常信息配置
     */
    READER_CONFIG_NOT_EXIST(false, 70000, "该医院未配置"),
    SCREEN_NOT_FOUND(false, 70001, "找不到对应的设备或设备配置项为空"),
    UPLOAD_FILE_IS_NULL(false, 70002, "上传文件不能为空"),
    FILE_OVER_MAXIMUM(false, 70003, "图片大小超过限制, 最大允许"),
    ILLEGAL_CHARACTER(false, 70004, "包含非法字符"),
    APK_NOT_FOUND(false, 70005, "未找到可用安装包"),
    REPEAT_UPLOAD(false, 70006, "该版本已存在, 请修改版本后再试"),
    HIS_OPEN_ID_CARD_NUM_NOT_FOUND(false, 70007, "hisOpenId 与 cardNum 不可同时为空!"),
    CARD_HAS_BIND_ERROR(false, 70008, "该卡已被绑定!"),
    TASK_ID_NOT_FOUND(false, 70009, "未知TASK ID!"),
    SCREEN_LOCATION_NOT_FOUND(false, 70010, "该设备未配置位置信息, 无法计算路径"),
    FEATURE_ID_MAPPING_ERROR(false, 70011, "医院科室至图聚featureId映射失败, 无法计算路径"),
    END_FEATURE_INFO_NOT_FOUND(false, 70012, "无终点信息, 无法计算路径"),
    FEATURE_INFO_ERROR(false, 70013, "无法规划路径"),
    CARD_NUM_NOT_FOUND(false, 70013, "您好，本院未查询到您的就诊信息，无法绑定卡片"),

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
