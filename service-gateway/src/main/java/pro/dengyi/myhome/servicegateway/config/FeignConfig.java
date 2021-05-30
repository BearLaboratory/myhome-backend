package pro.dengyi.myhome.servicegateway.config;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import pro.dengyi.myhome.myhomeutil.holder.GatewayTokenHolder;

import java.util.stream.Collectors;

/**
 * feign配置
 *
 * @author dengyi
 */
@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor headerInterceptor() {
        return template -> {
            String token = GatewayTokenHolder.getToken();
            template.header("token", token);
        };
    }


    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
    }


    @Bean
    Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }

}