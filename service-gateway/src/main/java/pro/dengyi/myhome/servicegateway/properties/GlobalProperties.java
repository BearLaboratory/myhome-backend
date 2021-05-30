package pro.dengyi.myhome.servicegateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 全局配置
 *
 * @author DengYi
 * @version v1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "global")
public class GlobalProperties {
    /**
     * 不进行拦截的url全路径
     */
    private List<String> excludePath;
}
