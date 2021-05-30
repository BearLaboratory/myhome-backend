package pro.dengyi.myhome.common.response;


/**
 * 相应类基本接口
 *
 * @author BLab
 */
public interface Response {

    /**
     * 响应状态
     *
     * @return Boolean
     */
    Boolean getStatus();

    /**
     * 响应code
     *
     * @return Integer
     */
    Integer getCode();

    /**
     * 响应消息
     *
     * @return String
     */
    String getMessage();
}
