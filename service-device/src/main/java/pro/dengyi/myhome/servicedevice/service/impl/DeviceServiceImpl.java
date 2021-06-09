package pro.dengyi.myhome.servicedevice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.dengyi.myhome.myhomemodel.business.device.Device;
import pro.dengyi.myhome.myhomemodel.business.vo.DevicePageVo;
import pro.dengyi.myhome.servicedevice.dao.DeviceDao;
import pro.dengyi.myhome.servicedevice.service.DeviceService;

import java.util.Date;
import java.util.HashMap;

/**
 * @author DengYi
 * @version v1.0
 */
@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceDao deviceDao;

    @Override
    public Page<Device> page(DevicePageVo vo) {
        Page<Device> page = new Page<>(vo.getPageNumber() == null ? 1 : vo.getPageNumber(), vo.getPageSize() == null ? 10 : vo.getPageSize());
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", vo.getName());
        params.put("online", vo.getOnline());
        params.put("publish", vo.getPublish());
        return deviceDao.customPage(page, params);
    }

    @Transactional
    @Override
    public void publishDevice(Device device) {
        Device deviceSaved = deviceDao.selectOne(new LambdaQueryWrapper<Device>().eq(Device::getId, device.getId()));
        deviceSaved.setPublish(true);
        deviceDao.updateById(deviceSaved);
    }

    @Transactional
    @Override
    public void addOrUpdateDevice(Device device) {
        if (StringUtils.isBlank(device.getId())) {
            //新增
            device.setCreateTime(new Date());
            device.setUpdateTime(new Date());
            deviceDao.insert(device);
        } else {
            //修改
            device.setUpdateTime(new Date());
            deviceDao.updateById(device);
        }
    }

    @Override
    public void controlDevice(Device device) {
        //1. 判断设备类型，只有控制型才能控制
        //2. 判断设备是否在线，在线才能控制
        //3. 判断设备类型，针对性的下发控制
    }
}
