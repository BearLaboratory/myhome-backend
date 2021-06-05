package pro.dengyi.myhome.servicebackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pro.dengyi.myhome.servicebackend.interceptors.LoginInterceptor;


/**
 * web基础配置
 * <p>
 * 网关中统一进行了跨域配置，单个项目中千万不要配置，否则会导致跨域
 *
 * @author BLab
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;


    /**
     * 跨域配置，注意这只是针对基础配置，如果涉及到自定义拦截器自行处理
     *
     * @param registry
     */

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/sysUser/login",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/v2/**",
                        "/swagger-ui.html/**");
    }
}
