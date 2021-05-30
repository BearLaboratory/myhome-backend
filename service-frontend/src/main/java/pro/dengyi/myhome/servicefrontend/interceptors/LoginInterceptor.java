package pro.dengyi.myhome.servicefrontend.interceptors;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pro.dengyi.myhome.myhomeutil.UserTokenUtil;
import pro.dengyi.myhome.myhomeutil.holder.UserHolder;
import pro.dengyi.myhome.myhomeutil.holder.UserHolderModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 *
 * @author DengYi
 * @version v1.0
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    /**
     * 进入控制器之前进行参数的封装
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            String token = request.getHeader("token");
            if (StringUtils.isNotBlank(token)) {
                UserHolderModel userHolderModel = UserTokenUtil.decToken(token);
                UserHolder.setUser(userHolderModel);
            }
        }
        return true;
    }

    /**
     * 控制器处理完以后删除数据防止内存泄漏
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        UserHolder.remove();
    }
}
