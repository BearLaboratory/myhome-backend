package pro.dengyi.myhome.servicedevice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.dengyi.myhome.common.exception.BusinessException;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.myhomemodel.business.device.Device;
import pro.dengyi.myhome.myhomemodel.business.device.DeviceCategory;
import pro.dengyi.myhome.myhomemodel.business.vo.DevicePageVo;
import pro.dengyi.myhome.servicedevice.dao.DeviceCategoryDao;
import pro.dengyi.myhome.servicedevice.dao.DeviceDao;
import pro.dengyi.myhome.servicedevice.service.DeviceService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author DengYi
 * @version v1.0
 */
@Slf4j
@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceDao deviceDao;
    @Autowired
    private DeviceCategoryDao deviceCategoryDao;


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
        //查询数据库数据，防止数据传输过程中被修改
        DeviceCategory deviceCategory = deviceCategoryDao.selectById(device.getCategoryId());
        Device realDevice = deviceDao.selectById(device.getId());
        List<String> canControlTypes = new ArrayList<>();
        canControlTypes.add("1");
        //1. 判断设备类型，只有控制型才能控制(测量型不能控制)
        if (!canControlTypes.contains(String.valueOf(deviceCategory.getType()))) {
            throw new BusinessException(ResponseEnum.DEVICE_TYPE_CAN_NOT_CONTROL);
        }
        //2. 判断设备是否在线，设备在线才能控制
        if (!realDevice.getOnline()) {
            throw new BusinessException(ResponseEnum.DEVICE_NOT_ONLINE);
        }
        //3. 根据子类型精细的组装控制报文
        switch (deviceCategory.getSubType()) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                log.error("设备类型未兼容");
        }
        //4. 下发控制报文
        sendCommand2Device(device.getId(), "");
    }

    /**
     * 向设备发送控制命令
     *
     * @param deviceId       设备ID
     * @param commandContent 设备控制命令（json格式）
     */
    private void sendCommand2Device(String deviceId, String commandContent) {
        //todo 向设备下发控制报文

    }
}
