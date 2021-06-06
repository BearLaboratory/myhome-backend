package pro.dengyi.myhome.servicebackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.dengyi.myhome.common.response.BaseResponse;
import pro.dengyi.myhome.myhomemodel.business.device.Device;
import pro.dengyi.myhome.myhomeutil.holder.SysUserHolder;
import pro.dengyi.myhome.servicebackend.apis.DeviceApi;
import pro.dengyi.myhome.servicebackend.service.DeviceService;

/**
 * @author DengYi
 * @version v1.0
 */
@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceApi deviceApi;

    @Override
    public BaseResponse addOrUpdate(Device device) {
        device.setDeveloperId(SysUserHolder.getUserId());
        device.setPublish(false);
        device.setOnline(false);
        return deviceApi.addOrUpdateDevice(device);
    }
}
