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
@ConfigurationProperties(prefix = "dayu")
public class DayuProperties {

    /**
     * accessKeyId
     */
    private String accessKeyId;

    /**
     * accessKeySecret
     */
    private String accessKeySecret;


    private String templateCode;

    private String signName;

}
