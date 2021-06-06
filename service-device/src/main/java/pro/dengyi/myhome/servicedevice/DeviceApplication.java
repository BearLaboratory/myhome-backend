package pro.dengyi.myhome.servicedevice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
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
                "pro.dengyi.myhome.servicedevice"
        })
@EntityScan(basePackages = {"pro.dengyi.myhome.myhomemodel.business"})
@MapperScan(basePackages = {"pro.dengyi.myhome.servicedevice.dao"})
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class DeviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceApplication.class, args);
    }
}
