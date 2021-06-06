package pro.dengyi.myhome.servicebackend.service;

import pro.dengyi.myhome.common.response.BaseResponse;
import pro.dengyi.myhome.myhomemodel.business.device.Device;

/**
 * @author DengYi
 * @version v1.0
 */
public interface DeviceService {
    BaseResponse addOrUpdate(Device device);
}
