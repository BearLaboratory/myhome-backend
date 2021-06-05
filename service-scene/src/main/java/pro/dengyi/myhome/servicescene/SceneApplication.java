package pro.dengyi.myhome.servicescene;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 *
 * @author DengYi
 * @version v1.0
 */

@ComponentScan(
        basePackages = {
                "pro.dengyi.myhome.common",
                "pro.dengyi.myhome.servicescene"
        })
@EntityScan(basePackages = {"pro.dengyi.myhome.myhomemodel.business"})
@MapperScan(basePackages = {"pro.dengyi.myhome.servicescene.dao"})
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class SceneApplication {
}
