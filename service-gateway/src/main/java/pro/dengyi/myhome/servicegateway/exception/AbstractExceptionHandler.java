package pro.dengyi.myhome.servicegateway.exception;


import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import pro.dengyi.myhome.common.exception.BusinessException;
import pro.dengyi.myhome.common.response.ResponseEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 抽象异常处理器
 *
 * @author dengyi
 */
public abstract class AbstractExceptionHandler {

    protected Map<String, Object> formatMessage(Throwable ex) {
        Map<String, Object> reqMap = new HashMap<>(2);
        if (ex instanceof NotFoundException) {
            reqMap.put("errorMessage", ResponseEnum.SERVICE_NOT_RUNNING.getMessage());
            reqMap.put("errorCode", ResponseEnum.SERVICE_NOT_RUNNING.getCode());
        } else if (ex instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) ex;
            if (responseStatusException.getStatus() == HttpStatus.NOT_FOUND) {
                reqMap.put("errorMessage", "资源不存在");
                reqMap.put("errorCode", 4041);
            } else {
                reqMap.put("errorMessage", responseStatusException.getReason());
                reqMap.put("errorCode", 99999);
            }
        } else if (ex instanceof BusinessException) {
            //业务异常
            reqMap.put("errorMessage", ex.getMessage());
            reqMap.put("errorCode", ((BusinessException) ex).getCode());
        }
        return reqMap;
    }

    protected Map<String, Object> buildErrorMap(Map<String, Object> formatMessageMap) {
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("status", false);
        resMap.put("code", formatMessageMap.get("errorCode"));
        resMap.put("message", formatMessageMap.get("errorMessage"));
        return resMap;
    }

}