package pro.dengyi.myhome.servicefrontend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.dengyi.myhome.common.exception.BusinessException;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.myhomemodel.business.device.Device;
import pro.dengyi.myhome.servicefrontend.dao.DeviceDao;
import pro.dengyi.myhome.servicefrontend.service.DeviceService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author BLab
 */
@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceDao deviceDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Device> listByRoomId(String familyId, String roomId, Boolean favorite) {
        HashMap<String, Object> params = new HashMap<>(3);
        params.put("familyId", familyId);
        params.put("roomId", roomId);
        params.put("favorite", favorite == null || !favorite ? null : true);
        List<Device> deviceList = deviceDao.selectListByFidRidFa(params);
        return deviceList;
    }

    @Transactional
    @Override
    public void changeFavorite(Device device) {
        Device deviceSaved = deviceDao.selectById(device.getId());
        deviceSaved.setFavorite(device.getFavorite());
        deviceSaved.setUpdateTime(new Date());
        deviceDao.updateById(deviceSaved);
    }

    @Override
    public void doControl(Device device) {
        Integer deviceType = device.getType();
        Device deviceSaved = deviceDao.selectById(device.getId());
        HashMap<String, Object> deviceOrder = new HashMap<>();
        switch (deviceType) {
            //控制型
            case 1:
                deviceSaved.setOpen(device.getOpen());
                deviceOrder.put("open", device.getOpen());
                break;
            //开关比例型
            case 131:
            case 132:
//                deviceSaved.setRate(device.getRate());
//                deviceSaved.setOpen(device.getRate() > 0);
//                deviceOrder.put("open", device.getRate() > 0);
//                deviceOrder.put("rate", device.getRate());
                break;
            default:
                throw new BusinessException(ResponseEnum.PARAM_ERROR);
        }
        deviceSaved.setUpdateTime(new Date());
        //更新数据库
        deviceDao.updateById(deviceSaved);
        deviceOrder.put("deviceId", deviceSaved.getId());
        //todo 发送控制报文
//        PubRequest request = new PubRequest();
//        request.setProductKey(product.getProductKey());
//        request.setMessageContent(Base64.encodeBase64String(JSON.toJSONString(deviceOrder).getBytes()));
//        request.setTopicFullName("/" + product.getProductKey() + "/" + deviceSaved.getId() + "/controled");
//        request.setQos(0); //目前支持QoS0和QoS1。
//        PubResponse response = defaultAcsClient.getAcsResponse(request);
//        if (!response.getSuccess()) {
//            throw new BusinessException(ResponseEnum.CONTROL_ERROR);
//
//        }
    }

    @Transactional
    @Override
    public void addDevice(Device device) {
        Device deviceSaved = deviceDao.selectById(device.getId());
        deviceSaved.setFamilyId(device.getFamilyId());
        deviceSaved.setRoomId(device.getRoomId());
        deviceSaved.setOnline(true);
        deviceSaved.setUpdateTime(new Date());
        deviceDao.updateById(deviceSaved);
    }
}
