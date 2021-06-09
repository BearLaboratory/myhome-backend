package pro.dengyi.myhome.servicedevice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pro.dengyi.myhome.myhomemodel.business.device.Device;
import pro.dengyi.myhome.myhomemodel.business.vo.DevicePageVo;

/**
 * @author DengYi
 * @version v1.0
 */
public interface DeviceService {
    /**
     * 条件分页查询
     *
     * @param vo
     * @return
     */
    Page<Device> page(DevicePageVo vo);

    void publishDevice(Device device);

    /**
     * 新增或修改设备
     *
     * @param device
     */
    void addOrUpdateDevice(Device device);

    void controlDevice(Device device);
}
