package pro.dengyi.myhome.servicedevice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
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
            //??????
            device.setCreateTime(new Date());
            device.setUpdateTime(new Date());
            deviceDao.insert(device);
        } else {
            //??????
            device.setUpdateTime(new Date());
            deviceDao.updateById(device);
        }
    }

    @Override
    public void controlDevice(Device device) {
        //????????????????????????????????????????????????????????????
        DeviceCategory deviceCategory = deviceCategoryDao.selectById(device.getCategoryId());
        Device realDevice = deviceDao.selectById(device.getId());
        List<String> canControlTypes = new ArrayList<>();
        canControlTypes.add("1");
        //1. ????????????????????????????????????????????????(?????????????????????)
        if (!canControlTypes.contains(String.valueOf(deviceCategory.getType()))) {
            throw new BusinessException(ResponseEnum.DEVICE_TYPE_CAN_NOT_CONTROL);
        }
        //2. ???????????????????????????????????????????????????
        if (!realDevice.getOnline()) {
            throw new BusinessException(ResponseEnum.DEVICE_NOT_ONLINE);
        }
        //3. ??????????????????????????????????????????
        switch (deviceCategory.getSubType()) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                log.error("?????????????????????");
        }
        //4. ??????????????????
        sendCommand2Device(device.getId(), "");
    }


    /**
     * ???????????????????????????
     *
     * @param deviceId       ??????ID
     * @param commandContent ?????????????????????json?????????
     */
    private void sendCommand2Device(String deviceId, String commandContent) {
        //todo ???????????????????????????

    }


    @Override
    public Boolean roomContainDevices(String roomId) {
        List<Device> devices = deviceDao.selectList(new LambdaQueryWrapper<Device>().eq(Device::getRoomId, roomId));
        return CollectionUtils.isEmpty(devices);
    }
}
