package pro.dengyi.myhome.common.response;

import lombok.NoArgsConstructor;

/**
 * 基础响应类
 *
 * @author BLab
 */
@NoArgsConstructor
public class BaseResponse implements Response {

    private Boolean status;
    private Integer code;
    private String message;

    /**
     * 枚举构造
     *
     * @param responseEnum 枚举值
     */
    public BaseResponse(ResponseEnum responseEnum) {
        this.status = responseEnum.getStatus();
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }

    @Override
    public Boolean getStatus() {
        return status;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
