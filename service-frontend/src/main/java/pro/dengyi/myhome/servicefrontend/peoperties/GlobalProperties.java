package pro.dengyi.myhome.servicefrontend.peoperties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里大鱼短信配置
 *
 * @author DengYi
 * @version v1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "global")
public class GlobalProperties {

    /**
     * 验证码过期时间，默认5分钟
     */
    private Integer validCodeExpireMinus = 5;

}
