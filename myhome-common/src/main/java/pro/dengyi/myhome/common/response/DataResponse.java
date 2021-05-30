package pro.dengyi.myhome.common.response;

import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 数据响应类
 *
 * @author BLab
 */
@NoArgsConstructor
public class DataResponse<T> implements Response, Serializable {

    private Boolean status;
    private Integer code;
    private String message;

    private T data;


    /**
     * 枚举构造
     *
     * @param responseEnum 枚举值
     */
    public DataResponse(ResponseEnum responseEnum, T data) {
        this.status = responseEnum.getStatus();
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        this.data = data;
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

    public T getData() {
        return data;
    }
}
