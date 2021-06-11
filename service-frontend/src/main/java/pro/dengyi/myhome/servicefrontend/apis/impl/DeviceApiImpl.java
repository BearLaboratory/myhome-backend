package pro.dengyi.myhome.servicefrontend.apis.impl;

import org.springframework.stereotype.Component;
import pro.dengyi.myhome.common.exception.BusinessException;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.servicefrontend.apis.DeviceApi;

/**
 * @author DengYi
 * @version v1.0
 */
@Component
public class DeviceApiImpl implements DeviceApi {
    @Override
    public Boolean roomContainDevices(String roomId) {
        throw new BusinessException(ResponseEnum.SERVICE_CALL_ERROR);
    }
}
