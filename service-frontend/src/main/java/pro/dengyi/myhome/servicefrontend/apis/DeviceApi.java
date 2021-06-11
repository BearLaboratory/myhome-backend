package pro.dengyi.myhome.servicefrontend.apis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 根据房间ID查询是否存在设备
     *
     * @param roomId 房间ID
     * @return 如果包含返回true, 如果不包含返回false
     */
    @GetMapping("/device/roomContainDevices")
    Boolean roomContainDevices(@RequestParam String roomId);
}
