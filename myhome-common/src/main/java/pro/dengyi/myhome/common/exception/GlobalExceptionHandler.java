package pro.dengyi.myhome.common.exception;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.NestedServletException;
import pro.dengyi.myhome.common.response.ResponseEnum;

import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理器
 *
 * @author dengy
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 系统异常统一处理
     *
     * @param e 异常
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Map<String, Object> customException(Exception e) {
        log.error("框架异常，信息为:", e);
        Map<String, Object> returnmap = new HashMap<>(3);
        returnmap.put("status", false);
        returnmap.put("code", ResponseEnum.SYSTEM_ERROR.getCode());
        returnmap.put("message", ResponseEnum.SYSTEM_ERROR.getMessage());
        return returnmap;
    }

    /**
     * 业务异常处理类
     *
     * @param be 业务异常类
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public Map<String, Object> businessException(BusinessException be) {
        log.error("业务异常，信息为:", be);
        Map<String, Object> returnmap = new HashMap<>(3);
        //执行状态
        returnmap.put("status", false);
        returnmap.put("code", be.getCode());
        returnmap.put("message", be.getMessage());
        return returnmap;
    }

    /**
     * 参数异常处理类
     *
     * @param me 参数异常类
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class, IllegalArgumentException.class})
    public Map<String, Object> parametersException(Exception me) {
        log.error("请求参数异常，信息为:", me);
        Map<String, Object> returnmap = new HashMap<>(3);
        //执行状态
        returnmap.put("status", false);
        returnmap.put("code", ResponseEnum.PARAM_ERROR.getCode());
        returnmap.put("message", ResponseEnum.PARAM_ERROR.getMessage());
        return returnmap;
    }

    /**
     * 请求方式异常
     *
     * @param me 参数异常类
     * @return
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Map<String, Object> methodException(Exception me) {
        log.error("请求方式异常，信息为:", me);
        Map<String, Object> returnmap = new HashMap<>(3);
        //执行状态
        returnmap.put("status", false);
        returnmap.put("code", ResponseEnum.PARAM_ERROR.getCode());
        returnmap.put("message", "请求方式异常");
        return returnmap;
    }

    /**
     * token异常处理
     *
     * @param ee 参数异常类
     * @return
     */
    @ExceptionHandler(ExpiredJwtException.class)
    public Map<String, Object> jwtExpireException(ExpiredJwtException ee) {
        log.error("jwt过期异常，信息为:", ee);
        Map<String, Object> returnmap = new HashMap<>(3);
        //执行状态
        returnmap.put("status", false);
        returnmap.put("code", ResponseEnum.TOKEN_EXPIRE.getCode());
        returnmap.put("message", ResponseEnum.TOKEN_EXPIRE.getMessage());
        return returnmap;
    }

    /**
     * token异常处理类
     *
     * @param ee 参数异常类
     * @return
     */
    @ExceptionHandler({MalformedJwtException.class, SignatureException.class})
    public Map<String, Object> tokenError(Exception ee) {
        log.error("token异常，信息为:", ee);
        Map<String, Object> returnmap = new HashMap<>(3);
        //执行状态
        returnmap.put("status", false);
        returnmap.put("code", ResponseEnum.TOKEN_ERROR.getCode());
        returnmap.put("message", ResponseEnum.TOKEN_ERROR.getMessage());
        return returnmap;
    }

    /**
     * 服务内部调用异常处理
     *
     * @param ee 参数异常类
     * @return
     */
    @ExceptionHandler({NestedServletException.class})
    public Map<String, Object> nestedServletException(Exception ee) {
        log.error("微服务间调用异常", ee);
        Map<String, Object> returnmap = new HashMap<>(3);
        //执行状态
        returnmap.put("status", false);
        returnmap.put("code", ResponseEnum.SERVICE_CALL_ERROR.getCode());
        returnmap.put("message", ResponseEnum.SERVICE_CALL_ERROR.getMessage());
        return returnmap;
    }

}
