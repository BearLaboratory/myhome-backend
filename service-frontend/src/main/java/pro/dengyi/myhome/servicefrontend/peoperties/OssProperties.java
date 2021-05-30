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
@ConfigurationProperties(prefix = "oss")
public class OssProperties {

    /**
     * accessKeyId
     */
    private String accessKeyId;

    /**
     * accessKeySecret
     */
    private String accessKeySecret;

    /**
     * endpoint
     */
    private String endpoint;

    private String bucketName;
    private String webUrl;

}
