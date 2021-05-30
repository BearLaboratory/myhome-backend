package pro.dengyi.myhome.servicegateway.filter;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import pro.dengyi.myhome.common.exception.BusinessException;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.myhomeutil.SysUserTokenUtil;
import pro.dengyi.myhome.myhomeutil.UserTokenUtil;
import pro.dengyi.myhome.myhomeutil.holder.GatewayTokenHolder;
import pro.dengyi.myhome.myhomeutil.holder.SysUserHolderModel;
import pro.dengyi.myhome.myhomeutil.holder.UserHolderModel;
import pro.dengyi.myhome.servicegateway.apis.UcenterApi;
import pro.dengyi.myhome.servicegateway.properties.GlobalProperties;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 登录过滤器
 *
 * @author DengYi
 * @version v1.0
 */
@Component
public class LoginFilter implements GlobalFilter, Ordered {
    @Autowired
    private UcenterApi ucenterApi;
    @Autowired
    private GlobalProperties globalProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        //系统中除了前台登录和后台登录，其他所有操作都需要登录
        if (globalProperties.getExcludePath().contains(path)) {
            return chain.filter(exchange);
        } else {
            //校验所有请求是否带token
            HttpHeaders headers = exchange.getRequest().getHeaders();
            String token = headers.getFirst("token");
            if (StringUtils.isBlank(token)) {
                throw new BusinessException(ResponseEnum.NEED_LOGIN);
            }

            //判断前后端
            if (path.startsWith("/frontend")) {
                try {
                    UserHolderModel userHolderModel = UserTokenUtil.decToken(token);
                    return chain.filter(exchange);
                } catch (Exception e) {
                    throw new BusinessException(ResponseEnum.TOKEN_ERROR);
                }

            } else {
                try {
                    SysUserHolderModel sysUserHolderModel = SysUserTokenUtil.decToken(token);
                    if (sysUserHolderModel.getIsSuperAdmin()) {
                        return chain.filter(exchange);
                    } else {
                        //缓存到到本地给feign用
                        GatewayTokenHolder.setToken(token);
                        List<String> allPermissionList = ucenterApi.getAllPermissionList();
                        GatewayTokenHolder.remove();
                        if (allPermissionList.contains(request.getURI().getPath())) {
                            return chain.filter(exchange);
                        } else {
                            throw new BusinessException(ResponseEnum.NO_PERMISSION);
                        }
                    }
                } catch (Exception e) {
                    //解析token的时的异常为token解析异常，有可能是过期也有可能是伪造token。直接抛给前端重新登录就行了
                    throw new BusinessException(ResponseEnum.TOKEN_ERROR);
                }

            }

        }

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
