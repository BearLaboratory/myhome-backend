package pro.dengyi.myhome.common.exception;


import pro.dengyi.myhome.common.response.ResponseEnum;

/**
 * 业务异常类
 *
 * @author dengy
 */
public class BusinessException extends RuntimeException {

    private Integer code;

    /**
     * 业务异常构造，response
     *
     * @param responseEnum 枚举
     */
    public BusinessException(ResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.code = responseEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }


}
