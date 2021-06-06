package pro.dengyi.myhome.servicefrontend.apis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import pro.dengyi.myhome.servicefrontend.apis.impl.DeviceApiImpl;

/**
 * 设备微服务api
 *
 * @author DengYi
 * @version v1.0
 */
@Primary
@FeignClient(value = "SERVICE-DEVICE", fallback = DeviceApiImpl.class)
public interface DeviceApi {
}
